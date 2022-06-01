package com.cwpad.rail.nrod.cif;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Set;

public class CIFDaysRunParser {
    public Set<DayOfWeek> parse(String daysRun) {
        Set<DayOfWeek> days = new HashSet<>();
        char[] d = daysRun.toCharArray();
        for (int i = 0; i < d.length; i++) {
            char v = d[i];
            if (v == '1') {
                days.add(DayOfWeek.of(i + 1));
            }
        }
        return days;
    }
}
