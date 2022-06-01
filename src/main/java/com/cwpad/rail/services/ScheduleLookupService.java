package com.cwpad.rail.services;

import com.cwpad.rail.model.TrainSchedule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

public interface ScheduleLookupService extends Bootstrappable {
    List<TrainSchedule> findByUid(String scheduleUid, LocalDate day);
    List<TrainSchedule> findByUid(LocalDate day, String... scheduleUid);
    List<TrainSchedule> findRecentByTiploc(String tiploc);
    List<TrainSchedule> findByTiplocAndTime(String tiploc, LocalDateTime time);
    List<TrainSchedule> findByTiplocAndDepartureTime(String tiploc, LocalDateTime time, boolean exact);
    List<TrainSchedule> findByHeadCode(LocalDate date, String headCode);
    List<TrainSchedule> findByHeadCode(LocalDate date, String headCode, String areaId);
    List<TrainSchedule> findByTrainServiceCode(LocalDate date, String trainServiceCode);
    List<TrainSchedule> findByTrainServiceCode(String... trainServiceCode);
    List<String> searchForUids(String searchText);
    void updateDateFilterForToday();
    Stream<TrainSchedule> all();
}
