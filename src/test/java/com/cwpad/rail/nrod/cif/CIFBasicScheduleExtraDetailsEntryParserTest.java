package com.cwpad.rail.nrod.cif;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CIFBasicScheduleExtraDetailsEntryParserTest {
    private CIFBasicScheduleExtraDetailsEntryParser parser;
    private CIFBasicScheduleExtraDetailsRecord record;

    @Before
    public void setUp() {
        parser = new CIFBasicScheduleExtraDetailsEntryParser();
        record = parser.parse("BX         EMY                                                                  ");
    }

    @Test
    public void uidCode() {
        assertNull(record.getUicCode());
    }

    @Test
    public void atocCode() {
        assertEquals("EM", record.getAtocCode());
    }

    @Test
    public void applicable() {
        assertTrue(record.isApplicable());
    }
}
