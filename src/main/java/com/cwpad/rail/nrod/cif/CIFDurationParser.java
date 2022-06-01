package com.cwpad.rail.nrod.cif;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.regex.Pattern;

class CIFDurationParser {
    private final Pattern minutes = Pattern.compile("\\d{2}");

    public Duration parse(String cifDuration) {
        if (cifDuration == null || cifDuration.trim().length() == 0) {
            throw new IllegalArgumentException();
        }

        Duration duration = Duration.of(0, ChronoUnit.SECONDS);
        if (minutes.matcher(cifDuration).matches()) {
            duration = duration.plusMinutes(Byte.parseByte(cifDuration));
        } else {
            if (cifDuration.contains("H")) {
                duration = duration.plusSeconds(30);
            }
            String minutes = cifDuration.replaceAll("H", "");
            if (minutes.length() > 0) {
                duration = duration.plusMinutes(Byte.parseByte(minutes));
            }
        }
        return duration;
    }
}
