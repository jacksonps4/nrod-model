package com.cwpad.rail.nrod.cif;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;

public class CIFHeaderEntryParserTest {
    private CIFHeaderEntryParser cifHeaderEntryParser;
    private CIFHeaderRecord record;

    @Before
    public void setUp() {
        cifHeaderEntryParser = new CIFHeaderEntryParser();
        record = cifHeaderEntryParser.parse(
                "HDTPS.UDFROC1.PD1510020210152319DFROC2Y       FA021015011016                    ");
    }

    @Test
    public void fileMainframeIdentity() {
        assertEquals("TPS.UDFROC1.PD151002", record.getFileMainframeIdentity());
    }

    @Test
    public void dateOfExtract() {
        assertEquals(LocalDate.of(2015, 10, 2), record.getDateOfExtract());
    }

    @Test
    public void timeOfExtract() {
        assertEquals(LocalTime.of(23, 19), record.getTimeOfExtract());
    }

    @Test
    public void currentFileRef() {
        assertEquals("DFROC2Y", record.getCurrentFileRef());
    }

    @Test
    public void lastFileRef() {
        assertEquals("", record.getLastFileRef());
    }

    @Test
    public void updateType() {
        assertEquals(CIFHeaderRecord.UpdateType.FULL, record.getUpdateType());
    }

    @Test
    public void version() {
        assertEquals("A", record.getVersion());
    }

    @Test
    public void userExtractStartDate() {
        assertEquals(LocalDate.of(2015, 10, 2), record.getUserExtractStartDate());
    }

    @Test
    public void userExtractEndDate() {
        assertEquals(LocalDate.of(2016, 10, 1), record.getUserExtractEndDate());
    }
}
