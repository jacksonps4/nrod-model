package com.cwpad.rail.nrod.cif;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

class CIFBasicScheduleEntryParser extends CIFBaseEntryParser {
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyMMdd");
    private final CIFDaysRunParser daysRunParser = new CIFDaysRunParser();

    @Override
    public CIFBasicScheduleRecord parse(String recordData) {
        checkType(CIFRecordType.BS, recordData);

        CIFBasicScheduleRecord record = new CIFBasicScheduleRecord();
        record.setCifTransactionType(CIFTransactionType.parse(recordData.substring(2, 3)));
        record.setTrainUid(recordData.substring(3, 9));
        record.setRunsFrom(parseDate(recordData.substring(9, 15)));
        record.setRunsTo(parseDate(recordData.substring(15, 21)));
        record.setDaysRun(parseDaysRun(recordData.substring(21, 28)));

        String bhx = recordData.substring(28, 29);
        if (bhx.trim().length() > 0) {
            record.setBankHolidayRunning(BankHolidayRunning.valueOf(bhx));
        }

        String trainStatus = recordData.substring(29, 30).trim();
        if (trainStatus.length() > 0) {
            record.setTrainStatus(TrainStatus.parse(trainStatus.charAt(0)));
        }

        String trainCategory = recordData.substring(30, 32).trim();
        if (trainCategory.length() > 0) {
            record.setTrainCategory(TrainCategory.valueOf(trainCategory));
        }

        String identity = recordData.substring(32, 36);
        if (identity.trim().length() > 0) {
            record.setIdentity(identity);
        }
        String headCode = recordData.substring(36, 40).trim();
        if (headCode.length() > 0) {
            record.setHeadCode(headCode);
        }

        String serviceCode = recordData.substring(41, 49).trim();
        if (serviceCode.length() > 0) {
            record.setServiceCode(serviceCode);
        }

        String powerType = recordData.substring(50, 53).trim();
        if (powerType.length() > 0) {
            record.setPowerType(PowerType.valueOf(powerType));
        }

        String timingLoad = recordData.substring(53, 57).trim();
        if (timingLoad.length() > 0) {
            record.setTimingLoad(new TimingLoad(timingLoad));
        }

        String speed = recordData.substring(57, 60).trim();
        if (speed.length() > 0) {
            record.setMaxSpeed(Integer.parseInt(speed));
        }

        String operatingCharacteristics = recordData.substring(60, 66).trim();
        if (operatingCharacteristics.length() > 0) {
            char[] characteristics = operatingCharacteristics.toCharArray();
            for (char characteristic : characteristics) {
                record.addOperatingCharacteristic(
                        OperatingCharacteristic.valueOf(String.valueOf(characteristic)));
            }
        }

        String seatingClass = recordData.substring(66, 67).trim();
        if (seatingClass.length() > 0) {
            record.setSeatingClass(SeatingClass.valueOf(seatingClass));
        }

        String sleepers = recordData.substring(67, 68).trim();
        if (sleepers.length() > 0) {
            record.setSleepers(Sleepers.valueOf(sleepers));
        }

        String reservations = recordData.substring(68, 69).trim();
        if (reservations.length() > 0) {
            record.setReservations(Reservations.valueOf(reservations));
        }

        // connectionIndicator: not used

        String cateringCodes = recordData.substring(70, 74).trim();
        if (cateringCodes.length() > 0) {
            char[] cc = cateringCodes.toCharArray();
            for (char cateringCode : cc) {
                record.addCatering(Catering.valueOf(String.valueOf(cateringCode)));
            }
        }

        String serviceBranding = recordData.substring(74, 78).trim();
        if (serviceBranding.length() > 0) {
            char[] sb = serviceBranding.toCharArray();
            for (char serviceBrand : sb) {
                record.addServiceBranding(ServiceBranding.valueOf(String.valueOf(serviceBrand)));
            }
        }

        record.setStpIndicator(StpIndicator.valueOf(recordData.substring(79, 80)));

        return record;
    }

    private Set<DayOfWeek> parseDaysRun(String daysRun) {
        if (daysRun.trim().length() > 0) {
            return daysRunParser.parse(daysRun);
        }
        return null;
    }

    private LocalDate parseDate(String date) {
        if (date.trim().length() > 0) {
            return LocalDate.from(dateFormatter.parse(date));
        }
        return null;
    }
}
