package com.cwpad.rail.nrod.cif;

import com.cwpad.rail.model.TrainSchedule;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CIFScheduleLookupServiceIT extends AbstractCIFScheduleLookupServiceIT {
    @Test
    public void londonToEdinburghOrigin() {
        if (scheduleLookupService == null) {
            return;
        }
        List<TrainSchedule> schedule = getLondonToEdinburgh();
        assertEquals(1, schedule.size());
        TrainSchedule trainSchedule = schedule.get(0);
        assertEquals("KNGX", trainSchedule.getOriginTiploc());
    }

    @Test
    public void londonToEdinburghDestination() {
        if (scheduleLookupService == null) {
            return;
        }
        List<TrainSchedule> schedule = getLondonToEdinburgh();
        assertEquals(1, schedule.size());
        TrainSchedule trainSchedule = schedule.get(0);
        assertEquals("EDINBUR", trainSchedule.getDestinationTiploc());
    }

    @Test
    public void newcastleToLondonOrigin() {
        if (scheduleLookupService == null) {
            return;
        }
        List<TrainSchedule> schedule = scheduleLookupService.findByUid("Y71771", LocalDate.of(2016, 10, 19));
        assertEquals(1, schedule.size());
        TrainSchedule trainSchedule = schedule.get(0);
        assertEquals("NWCSTLE", trainSchedule.getOriginTiploc());
    }

    @Test
    public void newcastleToLondonDeparture() {
        if (scheduleLookupService == null) {
            return;
        }
        List<TrainSchedule> schedule = getNewcastleToLondon();
        assertEquals(1, schedule.size());
        TrainSchedule trainSchedule = schedule.get(0);
        assertEquals(LocalTime.of(5, 25), trainSchedule.getOriginDepartureTime());
    }

    @Test
    public void newcastleToLondonDestination() {
        if (scheduleLookupService == null) {
            return;
        }
        List<TrainSchedule> schedule = getNewcastleToLondon();
        assertEquals(1, schedule.size());
        TrainSchedule trainSchedule = schedule.get(0);
        assertEquals("KNGX", trainSchedule.getDestinationTiploc());
    }

    @Test
    public void newcastleToLondonArrival() {
        if (scheduleLookupService == null) {
            return;
        }
        List<TrainSchedule> schedule = getNewcastleToLondon();
        assertEquals(1, schedule.size());
        TrainSchedule trainSchedule = schedule.get(0);
        assertEquals(LocalTime.of(8, 41), trainSchedule.getDestinationArrivalTime());
    }

    @Test
    public void londonCannonStToDartfordOrigin() {
        if (scheduleLookupService == null) {
            return;
        }

        List<TrainSchedule> schedule = getCannonStToDartford();
        assertEquals(1, schedule.size());
        TrainSchedule trainSchedule = schedule.get(0);
        assertEquals("CANONST", trainSchedule.getOriginTiploc());
    }

    @Test
    public void londonCannonStToDartfordDeparture() {
        if (scheduleLookupService == null) {
            return;
        }

        List<TrainSchedule> schedule = getCannonStToDartford();
        assertEquals(1, schedule.size());
        TrainSchedule trainSchedule = schedule.get(0);
        assertEquals(LocalTime.of(19, 37), trainSchedule.getOriginDepartureTime());
    }

    @Test
    public void londonCannonStToDartfordDestination() {
        if (scheduleLookupService == null) {
            return;
        }

        List<TrainSchedule> schedule = getCannonStToDartford();
        assertEquals(1, schedule.size());
        TrainSchedule trainSchedule = schedule.get(0);
        assertEquals("DARTFD", trainSchedule.getDestinationTiploc());
    }

    @Test
    public void londonCannonStToDartfordArrival() {
        if (scheduleLookupService == null) {
            return;
        }

        List<TrainSchedule> schedule = getCannonStToDartford();
        assertEquals(1, schedule.size());
        TrainSchedule trainSchedule = schedule.get(0);
        assertEquals(LocalTime.of(20, 21), trainSchedule.getDestinationArrivalTime());
    }
}
