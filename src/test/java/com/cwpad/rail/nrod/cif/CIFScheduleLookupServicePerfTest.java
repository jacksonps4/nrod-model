package com.cwpad.rail.nrod.cif;

import com.cwpad.rail.model.TrainSchedule;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CIFScheduleLookupServicePerfTest extends AbstractCIFScheduleLookupServiceIT {
    @Test
    public void timePerLookupByUidAndDay() {
        if (scheduleLookupService == null) {
            return;
        }

        long start = System.currentTimeMillis();
        final int numberOfLookups = 50000;
        List<TrainSchedule> schedule = null;
        for (int i = 0; i < numberOfLookups; i++) {
            schedule = scheduleLookupService.findByUid("Y71771", LocalDate.now());
        }

        long timeTaken = System.currentTimeMillis() - start;
        double msPerLookup = timeTaken / (double) numberOfLookups;
        logger.info(String.format("Mean time for lookup: %fms", msPerLookup));

        assertEquals(1, schedule.size());
        assertEquals("NWCSTLE", schedule.get(0).getOriginTiploc());
        assertEquals("KNGX", schedule.get(0).getDestinationTiploc());
        assertEquals(LocalTime.of(5, 25), schedule.get(0).getOriginDepartureTime());
        assertEquals(LocalTime.of(8, 41), schedule.get(0).getDestinationArrivalTime());
    }

    @Test
    public void timePerLookupByHeadCode() {
        if (scheduleLookupService == null) {
            return;
        }

        long start = System.currentTimeMillis();
        final int numberOfLookups = 2000;
        List<TrainSchedule> schedule = null;
        for (int i = 0; i < numberOfLookups; i++) {
            schedule = scheduleLookupService.findByHeadCode(LocalDate.of(2018, 6, 21),
                    "1Y05");
        }

        long timeTaken = System.currentTimeMillis() - start;
        double msPerLookup = timeTaken / (double) numberOfLookups;
        logger.info(String.format("Mean time for lookup: %fms", msPerLookup));

        assertEquals(3, schedule.size());
        assertEquals("NWCSTLE", schedule.get(2).getOriginTiploc());
        assertEquals("KNGX", schedule.get(2).getDestinationTiploc());
        assertEquals(LocalTime.of(5, 25), schedule.get(2).getOriginDepartureTime());
        assertEquals(LocalTime.of(8, 41), schedule.get(2).getDestinationArrivalTime());
    }
}
