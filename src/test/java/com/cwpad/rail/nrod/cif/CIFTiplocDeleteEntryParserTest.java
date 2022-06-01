package com.cwpad.rail.nrod.cif;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CIFTiplocDeleteEntryParserTest {
    private CIFTiplocDeleteEntryParser parser;
    private CIFTiplocDeleteRecord record;

    @Before
    public void setUp() {
        parser = new CIFTiplocDeleteEntryParser();
        record = parser.parse("TDKNGX                                                                         ");
    }

    @Test
    public void tiploc() {
        assertEquals("KNGX", record.getTiploc());
    }
}
