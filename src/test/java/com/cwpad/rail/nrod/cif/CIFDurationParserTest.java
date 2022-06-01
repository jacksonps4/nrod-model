package com.cwpad.rail.nrod.cif;

import org.junit.Before;
import org.junit.Test;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class CIFDurationParserTest {
    private CIFDurationParser parser;

    @Before
    public void setUp() {
        parser = new CIFDurationParser();
    }

    @Test(expected = IllegalArgumentException.class)
    public void blankValue() {
        parser.parse("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullValue() {
        parser.parse(null);
    }

    @Test
    public void zero() {
        assertEquals(Duration.ofMinutes(0), parser.parse("0"));
    }

    @Test
    public void minutes() {
        assertEquals(Duration.ofMinutes(5), parser.parse("5"));
    }

    @Test
    public void half() {
        assertEquals(Duration.ofSeconds(30), parser.parse("H"));
    }

    @Test
    public void minutesWithHalf() {
        assertEquals(Duration.ofMinutes(3).plusSeconds(30), parser.parse("3H"));
    }

    @Test
    public void minutesGt10() {
        assertEquals(Duration.ofMinutes(25), parser.parse("25"));
    }

    @Test
    public void minutesEdge1() {
        assertEquals(Duration.ofMinutes(10), parser.parse("10"));
    }

    @Test
    public void minutesEdge2() {
        assertEquals(Duration.ofMinutes(59), parser.parse("59"));
    }
}
