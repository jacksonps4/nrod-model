package com.cwpad.rail.nrod.cif;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;

import static org.junit.Assert.*;

public class CIFTerminatingLocationEntryParserTest {
    private CIFTerminatingLocationEntryParser parser;
    private CIFTerminatingLocationRecord record;

    @Before
    public void setUp() {
        parser = new CIFTerminatingLocationEntryParser();
        record = parser.parse("LTSTPX    1855 18554     TF                                                     ");
    }

    @Test
    public void tiploc() {
        assertEquals("STPX", record.getTiploc());
    }

    @Test
    public void scheduledArrival() {
        assertEquals(LocalTime.of(18, 55), record.getScheduledArrival());
    }

    @Test
    public void publicArrival() {
        assertEquals(LocalTime.of(18, 55), record.getPublicArrival());
    }

    @Test
    public void platform() {
        assertEquals("4", record.getPlatform());
    }

    @Test
    public void path() {
        assertNull(record.getPath());
    }

    @Test
    public void activities() {
        assertThat(record.getActivities(), Matchers.containsInAnyOrder(Activity.TF));
    }
}
