package com.cwpad.rail.nrod.cif;

import com.cwpad.rail.model.TrainOperator;
import com.cwpad.rail.model.TrainPass;
import com.cwpad.rail.model.TrainSchedule;
import com.cwpad.rail.services.AbstractBootstrappable;
import com.cwpad.rail.services.Logger;
import com.cwpad.rail.services.ScheduleLookupService;
import com.cwpad.rail.services.TrainOperatingCompanyRepository;

import java.io.Reader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class CIFScheduleLookupService extends AbstractBootstrappable implements ScheduleLookupService {
    private final Logger logger = Logger.getInstance(getClass());

    private final Map<CIFScheduleKey, TrainSchedule> scheduleRecords = new ConcurrentHashMap<>();

    private final TrainOperatingCompanyRepository trainOperatingCompanyRepository;

    public CIFScheduleLookupService(TrainOperatingCompanyRepository trainOperatingCompanyRepository) {
        this.trainOperatingCompanyRepository = trainOperatingCompanyRepository;
    }

    @Override
    public void bootstrap(Reader reader) {
        updateDateFilterForToday();

        Iterator<CIFRecord> itr = new CIFParser(reader).iterator();
        CIFRecord record;
        CIFScheduleKey key = null;
        while (itr.hasNext() && !isInterrupted()) {
            try {
                record = itr.next();
                if (record instanceof CIFBasicScheduleRecord) {
                    CIFBasicScheduleRecord bs = (CIFBasicScheduleRecord) record;
                    key = createKey(bs);
                    processBasicSchedule(bs, key);
                } else if (record instanceof CIFBasicScheduleExtraDetailsRecord) {
                    CIFBasicScheduleExtraDetailsRecord bx = (CIFBasicScheduleExtraDetailsRecord) record;
                    processBasicScheduleExtraDetails(bx, key);
                } else if (record instanceof CIFOriginLocationRecord) {
                    CIFOriginLocationRecord lo = (CIFOriginLocationRecord) record;
                    addOriginLocation(lo, key);
                } else if (record instanceof CIFIntermediateLocationRecord) {
                    CIFIntermediateLocationRecord li = (CIFIntermediateLocationRecord) record;
                    addIntermediateLocation(li, key);
                } else if (record instanceof CIFTerminatingLocationRecord) {
                    CIFTerminatingLocationRecord lt = (CIFTerminatingLocationRecord) record;
                    addTerminatingLocation(lt, key);
                }
            } catch (RuntimeException e) {
                logger.error("Failed to bootstrap schedule service", e);
            }
        }
    }

    public void updateDateFilterForToday() {}

    void processBasicSchedule(CIFBasicScheduleRecord bs, CIFScheduleKey key) {
        TrainSchedule schedule = map(bs);
        switch (bs.getCifTransactionType()) {
            case NEW:
            case REVISE:
                scheduleRecords.put(key, schedule);
                break;
            case DELETE:
                scheduleRecords.remove(key);
                break;
        }
    }

    void processBasicScheduleExtraDetails(CIFBasicScheduleExtraDetailsRecord bx, CIFScheduleKey key) {
        if (scheduleRecords.containsKey(key)) {
            TrainSchedule schedule = scheduleRecords.get(key);
            TrainOperator trainOperator = trainOperatingCompanyRepository.searchByAtocCode(bx.getAtocCode());
            if (trainOperator != null) {
                schedule.setTrainOperator(trainOperator);
            }
        }
    }

    CIFScheduleKey createKey(CIFBasicScheduleRecord record) {
        return new CIFScheduleKey(record.getTrainUid(), record.getRunsFrom(), record.getRunsTo(), record.getDaysRun(),
                record.getStpIndicator());
    }

    TrainSchedule map(CIFBasicScheduleRecord bs) {
        TrainSchedule schedule = new TrainSchedule(bs.getTrainUid());
        schedule.setValidFrom(bs.getRunsFrom());
        schedule.setValidTo(bs.getRunsTo());
        schedule.setTrainIdentity(bs.getIdentity());
        ScheduleTransactionType scheduleTransactionType = ScheduleTransactionType.parse(bs.getStpIndicator().toString());
        schedule.setScheduleTransactionType(TrainSchedule.ScheduleTransactionType.valueOf(scheduleTransactionType.name()));
        schedule.setHeadCode(bs.getHeadCode());
        schedule.setOperatingCharacteristics(bs.getOperatingCharacteristics() != null ?
            bs.getOperatingCharacteristics().stream()
            .map(OperatingCharacteristic::getDescription)
            .collect(Collectors.toList()) : Collections.EMPTY_LIST);
        schedule.setServiceCode(bs.getServiceCode());
        schedule.setMaxSpeed(bs.getMaxSpeed());
        schedule.setPowerType(bs.getPowerType() != null ? bs.getPowerType().getDescription() : null);
        schedule.setCatering(bs.getCatering() != null ?
                bs.getCatering().stream()
                    .map(Catering::getDescription)
                    .collect(Collectors.toList()) : Collections.EMPTY_LIST);
        schedule.setSeatingClass(bs.getSeatingClass() != null ? bs.getSeatingClass().getDescription() : null);
        return schedule;
    }

    TrainPass map(CIFIntermediateLocationRecord lo, String trainUid) {
        TrainPass pass = new TrainPass(trainUid);
        pass.setTiploc(lo.getTiploc());
        pass.setPlatform(lo.getPlatform());
        if (lo.getScheduledPass() != null) {
            pass.setPassTime(lo.getScheduledPass());
            pass.setCallingPoint(false);
        } else {
            pass.setArrivalTime(lo.getScheduledArrival());
            pass.setCallingPoint(true);
        }
        if (lo.getScheduledDeparture() != null) {
            pass.setDepartureTime(lo.getScheduledDeparture());
        }
        return pass;
    }

    void addIntermediateLocation(CIFIntermediateLocationRecord lo, CIFScheduleKey key) {
        TrainSchedule schedule = scheduleRecords.get(key);
        List<TrainPass> passes = schedule.getPasses();
        passes.add(map(lo, key.getTrainUid()));
    }

    void addOriginLocation(CIFOriginLocationRecord lo, CIFScheduleKey key) {
        TrainSchedule schedule = scheduleRecords.get(key);
        schedule.setOriginTiploc(lo.getTiploc());
        schedule.setOriginDepartureTime(lo.getScheduledDeparture());
        schedule.setOriginPlatform(lo.getPlatform());
    }

    void addTerminatingLocation(CIFTerminatingLocationRecord lt, CIFScheduleKey key) {
        TrainSchedule schedule = scheduleRecords.get(key);
        schedule.setDestinationArrivalTime(lt.getScheduledArrival());
        schedule.setDestinationTiploc(lt.getTiploc());
        schedule.setDestinationPlatform(lt.getPlatform());
    }

    @Override
    public List<TrainSchedule> findByUid(String scheduleUid, LocalDate day) {
        return scheduleRecords.keySet().stream()
                .filter(k -> scheduleUid.equals(k.getTrainUid()))
                .filter(createScheduleDateFilter(day))
                .sorted(this::compareStpIndicators)
                .map(scheduleRecords::get)
                .collect(Collectors.toList());
    }

    private int compareStpIndicators(CIFScheduleKey a, CIFScheduleKey b) {
        return a.getStpIndicator().toString().compareTo(b.getStpIndicator().toString());
    }

    private Predicate<CIFScheduleKey> createScheduleDateFilter(LocalDate day) {
        return k -> k.getDaysRun().contains(day.getDayOfWeek())
                        && ((day.isEqual(k.getRunsFrom())
                        || day.isEqual(k.getRunsTo()))
                        || ((day.isAfter(k.getRunsFrom()) && day.isBefore(k.getRunsTo()))));
    }

    @Override
    public List<TrainSchedule> findByUid(LocalDate day, String... scheduleUids) {
        List<String> scheduleUidValues = Arrays.asList(scheduleUids);
        return scheduleRecords.keySet().stream()
                .filter(k -> scheduleUidValues.contains(k.getTrainUid()))
                .filter(createScheduleDateFilter(day))
                .sorted(this::compareStpIndicators)
                .map(scheduleRecords::get)
                .collect(Collectors.toList());
    }

    public List<TrainSchedule> findByTiplocAndTime(String tiploc, LocalDateTime time) {
        return scheduleRecords.keySet().stream()
                .filter(createScheduleDateFilter(time.toLocalDate()))
                .map(scheduleRecords::get)
                .filter(schedule -> tiploc.equals(schedule.getOriginTiploc()))
                .filter(schedule -> schedule.getOriginDepartureTime().isAfter(time.toLocalTime().minusMinutes(10))
                        && schedule.getOriginDepartureTime().isBefore(time.toLocalTime().plusHours(1)))
                .collect(Collectors.toList());
    }

    public List<TrainSchedule> findByTiplocAndDepartureTime(String tiploc, LocalDateTime time, boolean exact) {
        return scheduleRecords.keySet().stream()
                .filter(createScheduleDateFilter(time.toLocalDate()))
                .map(k -> scheduleRecords.get(k))
                .filter(schedule -> originOrPass(schedule, tiploc, time, exact))
                .collect(Collectors.toList());
    }

    private boolean originOrPass(TrainSchedule schedule, String tiploc, LocalDateTime time, boolean exact) {
        if (tiploc.equals(schedule.getOriginTiploc()) && (exact ? time.toLocalTime().equals(schedule.getOriginDepartureTime())
            : isNear(schedule.getOriginDepartureTime(), time, 15))) {
            return true;
        }
        for (TrainPass pass : schedule.getPasses()) {
            if (tiploc.equals(pass.getTiploc()) && pass.getDepartureTime() != null
                    && (exact ? time.toLocalTime().equals(pass.getDepartureTime().withSecond(0)) :
                    isNear(time.toLocalTime(), pass.getDepartureTime().withSecond(0), 15))) {
                return true;
            }
        }
        return false;
    }

    boolean isNear(LocalTime trainTime, LocalDateTime time, int range) {
        return trainTime.isAfter(time.toLocalTime().minusMinutes(range))
                && trainTime.isBefore(time.toLocalTime().plusMinutes(range));
    }

    boolean isNear(LocalTime trainTime, LocalTime time, int range) {
        return trainTime.isAfter(time.minusMinutes(range))
                && trainTime.isBefore(time.plusMinutes(range));
    }

    public List<TrainSchedule> findRecentByTiploc(String tiploc) {
        return findByTiplocAndTime(tiploc, LocalDateTime.now());
    }

    public List<TrainSchedule> findByHeadCode(LocalDate date, String headCode) {
        return scheduleRecords.entrySet().stream()
                .filter(e -> headCode.equals(e.getValue().getTrainIdentity()))
                .filter(e -> createScheduleDateFilter(date).test(e.getKey()))
                .map(e -> e.getValue())
                .collect(Collectors.toList());
    }

    @Override
    public List<TrainSchedule> findByTrainServiceCode(LocalDate date, String trainServiceCode) {
        return scheduleRecords.entrySet().stream()
                .filter(e -> trainServiceCode.equals(e.getValue().getServiceCode()))
                .filter(e -> createScheduleDateFilter(date).test(e.getKey()))
                .map(e -> e.getValue())
                .collect(Collectors.toList());
    }

    @Override
    public List<TrainSchedule> findByHeadCode(LocalDate date, String headCode, String areaId) {
        return findByHeadCode(date, headCode);
    }

    @Override
    public List<TrainSchedule> findByTrainServiceCode(String... trainServiceCodes) {
        return Arrays.asList(trainServiceCodes).stream()
                .map(serviceCode -> findByTrainServiceCode(LocalDate.now(), serviceCode))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> searchForUids(String searchText) {
        if (searchText.length() < 3) {
            throw new IllegalArgumentException("Search text length must be at least 3");
        }
        return scheduleRecords.keySet().stream()
                .map(scheduleKey -> scheduleKey.getTrainUid().toUpperCase())
                .filter(uid -> uid.startsWith(searchText))
                .collect(Collectors.toList());
    }

    public Stream<TrainSchedule> all() {
        return scheduleRecords.values()
                .stream();
    }
}
