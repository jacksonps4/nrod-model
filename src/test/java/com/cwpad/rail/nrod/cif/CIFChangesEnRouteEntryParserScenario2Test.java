package com.cwpad.rail.nrod.cif;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CIFChangesEnRouteEntryParserScenario2Test {
    private CIFChangesEnRouteEntryParser cifChangesEnRouteEntryParser;
    private CIFChangesEnRouteRecord record;

    @Before
    public void setUp() {
        cifChangesEnRouteEntryParser = new CIFChangesEnRouteEntryParser();
        record = cifChangesEnRouteEntryParser.parse(
                "CRSHRWBY  XX1G2532101222610001DMUE   090      S R T                             ");
    }

    @Test
    public void tiploc() {
        assertEquals("SHRWBY", record.getTiploc());
    }

    @Test
    public void trainCategory() {
        assertEquals(TrainCategory.XX, record.getTrainCategory());
    }

    @Test
    public void trainIdentity() {
        assertEquals("1G25", record.getIdentity());
    }

    @Test
    public void headCode() {
        assertEquals("3210", record.getHeadCode());
    }

    @Test
    public void trainServiceCode() {
        assertEquals("22261000", record.getServiceCode());
    }

    @Test
    public void powerType() {
        assertEquals(PowerType.DMU, record.getPowerType());
    }

    @Test
    public void timingLoad() {
        assertEquals("E", record.getTimingLoad().timingLoadType());
    }

    @Test
    public void speed() {
        assertEquals(Integer.valueOf(90), record.getMaxSpeed());
    }

    @Test
    public void operatingCharacteristics() {
        assertNull(record.getOperatingCharacteristics());
    }

    @Test
    public void seatingClass() {
        assertEquals(SeatingClass.S, record.getSeatingClass());
    }

    @Test
    public void sleepers() {
        assertNull(record.getSleepers());
    }

    @Test
    public void reservations() {
        assertEquals(Reservations.R, record.getReservations());
    }

    @Test
    public void cateringCode() {
        assertThat(record.getCatering(), Matchers.contains(Catering.T));
    }

    @Test
    public void serviceBranding() {
        assertNull(record.getServiceBranding());
    }
}
