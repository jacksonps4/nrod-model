package com.cwpad.rail.nrod.cif;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CIFTiplocInsertEntryParserTest {
    private CIFTiplocInsertEntryParser parser;
    private CIFTiplocInsertRecord record;

    @Before
    public void setUp() {
        parser = new CIFTiplocInsertEntryParser();
        record = parser.parse("TIKNGX   00612100QLONDON KINGS CROSS        543112901KGXLONDON KINGS X         ");
    }

    @Test
    public void tiploc() {
        assertEquals("KNGX", record.getTiploc());
    }

    @Test
    public void capitalsIdentification() {
        assertEquals("00", record.getCapitalsIdentification());
    }

    @Test
    public void nationalLocationCode() {
        assertEquals(612100, record.getNationalLocationCode());
    }

    @Test
    public void nalcoCheckCharacter() {
        assertEquals(Character.valueOf('Q'), record.getNlcCheckCharacter().get());
    }

    @Test
    public void tpsDescription() {
        assertEquals("LONDON KINGS CROSS", record.getTpsDescription().get());
    }

    @Test
    public void stanox() {
        assertEquals(Integer.valueOf(54311), record.getStanox().get());
    }

    @Test
    public void postOfficeLocationCode() {
        assertEquals(Integer.valueOf(2901), record.getPostOfficeLocationCode().get());
    }

    @Test
    public void crsCode() {
        assertEquals("KGX", record.getCrsCode().get());
    }

    @Test
    public void crsDescription() {
        assertEquals("LONDON KINGS X", record.getDescription().get());
    }
}
