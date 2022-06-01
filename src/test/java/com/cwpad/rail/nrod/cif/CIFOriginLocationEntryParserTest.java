package com.cwpad.rail.nrod.cif;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.*;

public class CIFOriginLocationEntryParserTest {
    private CIFOriginLocationEntryParser entryParser;
    private CIFOriginLocationRecord record;

    @Before
    public void setUp() {
        entryParser = new CIFOriginLocationEntryParser();
        record = entryParser.parse("LOFRMPRSG 1221H000010 SL2   HTB                                                 ");
    }

    @Test
    public void tiploc() {
        assertEquals("FRMPRSG", record.getTiploc());
    }

    @Test
    public void scheduledDeparture() {
        assertEquals(LocalTime.of(12, 21).plus(30, ChronoUnit.SECONDS), record.getScheduledDeparture());
    }

    @Test
    public void publicDeparture() {
        assertEquals(LocalTime.of(0, 0), record.getPublicDeparture());
    }

    @Test
    public void platform() {
        assertEquals("10", record.getPlatform());
    }

    @Test
    public void line() {
        assertEquals("SL2", record.getLine());
    }

    @Test
    public void engineeringAllowance() {
        assertNull(record.getEngineeringAllowance());
    }

    @Test
    public void pathingAllowance() {
        assertEquals(Duration.ofSeconds(30), record.getPathingAllowance());
    }

    @Test
    public void activities() {
        assertThat(record.getActivities(), Matchers.contains(Activity.TB));
    }

    @Test
    public void performanceAllowance() {
        assertNull(record.getPerformanceAllowance());
    }
}
