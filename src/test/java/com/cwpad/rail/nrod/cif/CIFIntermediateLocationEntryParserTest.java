package com.cwpad.rail.nrod.cif;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;

import static org.junit.Assert.*;

public class CIFIntermediateLocationEntryParserTest {
    private CIFIntermediateLocationEntryParser parser;
    private CIFIntermediateLocationRecord record;

    @Before
    public void setUp() {
        parser = new CIFIntermediateLocationEntryParser();
        record = parser.parse("LIYORK    2131 2134      213121345  AL    T K                                   ");
    }

    @Test
    public void location() {
        assertEquals("YORK", record.getTiploc());
    }

    @Test
    public void scheduledArrival() {
        assertEquals(LocalTime.of(21, 31), record.getScheduledArrival());
    }

    @Test
    public void scheduledDeparture() {
        assertEquals(LocalTime.of(21, 34), record.getScheduledDeparture());
    }

    @Test
    public void publicArrival() {
        assertEquals(LocalTime.of(21, 31), record.getPublicArrival());
    }

    @Test
    public void publicDeparture() {
        assertEquals(LocalTime.of(21, 34), record.getPublicDeparture());
    }

    @Test
    public void platform() {
        assertEquals("5", record.getPlatform());
    }

    @Test
    public void line() {
        assertEquals("AL", record.getLine());
    }

    @Test
    public void path() {
        assertNull(record.getPath());
    }

    @Test
    public void activities() {
        assertThat(record.getActivities(), Matchers.containsInAnyOrder(Activity.T, Activity.K));
    }

    @Test
    public void engineeringAllowance() {
        assertNull(record.getEngineeringAllowance());
    }

    @Test
    public void pathingAllowance() {
        assertNull(record.getPathingAllowance());
    }

    @Test
    public void performanceAllowance() {
        assertNull(record.getPerformanceAllowance());
    }
}
