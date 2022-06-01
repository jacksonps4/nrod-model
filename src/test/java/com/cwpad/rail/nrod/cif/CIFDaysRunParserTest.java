package com.cwpad.rail.nrod.cif;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.time.DayOfWeek;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CIFDaysRunParserTest {
    @Test
    public void mondayOnly() {
        CIFDaysRunParser parser = new CIFDaysRunParser();
        Set<DayOfWeek> days = parser.parse("1000000");
        assertThat(days, Matchers.contains(DayOfWeek.MONDAY));
    }

    @Test
    public void tuesdayOnly() {
        CIFDaysRunParser parser = new CIFDaysRunParser();
        Set<DayOfWeek> days = parser.parse("0100000");
        assertThat(days, Matchers.contains(DayOfWeek.TUESDAY));
    }

    @Test
    public void weekDays() {
        CIFDaysRunParser parser = new CIFDaysRunParser();
        Set<DayOfWeek> days = parser.parse("1111100");
        assertEquals(5, days.size());
        assertThat(days, Matchers.containsInAnyOrder(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
                DayOfWeek.THURSDAY, DayOfWeek.FRIDAY));
    }

    @Test
    public void weekends() {
        CIFDaysRunParser parser = new CIFDaysRunParser();
        Set<DayOfWeek> days = parser.parse("0000011");
        assertEquals(2, days.size());
        assertThat(days, Matchers.containsInAnyOrder(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY));
    }
}
