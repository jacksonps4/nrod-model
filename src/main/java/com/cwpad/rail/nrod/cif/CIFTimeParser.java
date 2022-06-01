package com.cwpad.rail.nrod.cif;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

class CIFTimeParser {
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");

    public LocalTime parseTime(String cifTime) {
        if (cifTime.length() < 4) {
            throw new IllegalArgumentException();
        }

        LocalTime time = LocalTime.from(timeFormatter.parse(cifTime.substring(0, 4)));
        if (cifTime.endsWith("H")) {
            time = time.plus(30, ChronoUnit.SECONDS);
        }
        return time;
    }
}
