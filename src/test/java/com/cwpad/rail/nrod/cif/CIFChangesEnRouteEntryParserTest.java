package com.cwpad.rail.nrod.cif;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CIFChangesEnRouteEntryParserTest {
    private CIFChangesEnRouteEntryParser parser;
    private CIFChangesEnRouteRecord record;

    @Before
    public void setUp() {
        parser = new CIFChangesEnRouteEntryParser();
        record = parser.parse("CRLESTER  XX1C911291122152000 HST    110      B S C           12345             ");
    }

    @Test
    public void tiploc() {
        assertEquals("LESTER", record.getTiploc());
    }

    @Test
    public void trainCategory() {
        assertEquals(TrainCategory.XX, record.getTrainCategory());
    }

    @Test
    public void identity() {
        assertEquals("1C91", record.getIdentity());
    }

    @Test
    public void headCode() {
        assertEquals("1291", record.getHeadCode());
    }

    @Test
    public void serviceCode() {
        assertEquals("22152000", record.getServiceCode());
    }

    @Test
    public void powerType() {
        assertEquals(PowerType.HST, record.getPowerType());
    }

    @Test
    public void timingLoad() {
        assertNull(record.getTimingLoad());
    }

    @Test
    public void speed() {
        assertEquals(Integer.valueOf(110), record.getMaxSpeed());
    }

    @Test
    public void operatingCharacteristics() {
        assertNull(record.getOperatingCharacteristics());
    }

    @Test
    public void seatingClass() {
        assertEquals(SeatingClass.B, record.getSeatingClass());
    }

    @Test
    public void sleepers() {
        assertNull(record.getSleepers());
    }

    @Test
    public void reservations() {
        assertEquals(Reservations.S, record.getReservations());
    }

    @Test
    public void catering() {
        assertThat(record.getCatering(), Matchers.containsInAnyOrder(Catering.C));
    }

    @Test
    public void serviceBrand() {
        assertNull(record.getServiceBranding());
    }

    @Test
    public void uicCode() {
        assertEquals("12345", record.getUicCode());
    }
}
