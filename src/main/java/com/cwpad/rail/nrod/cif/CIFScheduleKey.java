package com.cwpad.rail.nrod.cif;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

class CIFScheduleKey {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.BASIC_ISO_DATE;
    private final String trainUid;
    private final int runsFrom;
    private final int runsTo;
    private final Set<DayOfWeek> daysRun;
    private final char stpIndicator;

    public CIFScheduleKey(String trainUid, LocalDate runsFrom, LocalDate runsTo, Set<DayOfWeek> daysRun,
                          StpIndicator stpIndicator) {
        this.trainUid = trainUid.intern();
        this.runsFrom = Integer.parseInt(DATE_FORMATTER.format(runsFrom));
        this.runsTo = Integer.parseInt(DATE_FORMATTER.format(runsTo));
        this.daysRun = daysRun;
        this.stpIndicator = stpIndicator.toString().charAt(0);
    }

    public String getTrainUid() {
        return trainUid;
    }

    public LocalDate getRunsFrom() {
        return LocalDate.from(DATE_FORMATTER.parse(String.valueOf(runsFrom)));
    }

    public LocalDate getRunsTo() {
        return LocalDate.from(DATE_FORMATTER.parse(String.valueOf(runsTo)));
    }

    public Set<DayOfWeek> getDaysRun() {
        return daysRun;
    }

    public StpIndicator getStpIndicator() {
        return StpIndicator.valueOf(String.valueOf(stpIndicator));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CIFScheduleKey that = (CIFScheduleKey) o;

        if (runsFrom != that.runsFrom) return false;
        if (runsTo != that.runsTo) return false;
        if (stpIndicator != that.stpIndicator) return false;
        if (!trainUid.equals(that.trainUid)) return false;
        return daysRun.equals(that.daysRun);
    }

    @Override
    public int hashCode() {
        int result = trainUid.hashCode();
        result = 31 * result + runsFrom;
        result = 31 * result + runsTo;
        result = 31 * result + daysRun.hashCode();
        result = 31 * result + (int) stpIndicator;
        return result;
    }
}
