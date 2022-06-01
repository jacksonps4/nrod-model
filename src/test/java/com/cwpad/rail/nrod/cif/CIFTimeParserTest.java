package com.cwpad.rail.nrod.cif;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.assertEquals;

public class CIFTimeParserTest {
    private CIFTimeParser cifTimeParser;

    @Before
    public void setUp() {
        cifTimeParser = new CIFTimeParser();
    }

    @Test
    public void basic() {
        assertEquals(LocalTime.of(15, 31), cifTimeParser.parseTime("1531"));
    }

    @Test
    public void withHalf() {
        assertEquals(LocalTime.of(1, 12).plus(30, ChronoUnit.SECONDS), cifTimeParser.parseTime("0112H"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void empty() {
        cifTimeParser.parseTime("");
    }

    @Test(expected = DateTimeParseException.class)
    public void invalid() {
        cifTimeParser.parseTime("cUYFdsa");
    }
}
