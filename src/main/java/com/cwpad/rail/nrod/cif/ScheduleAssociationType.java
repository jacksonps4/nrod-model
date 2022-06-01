package com.cwpad.rail.nrod.cif;

public enum ScheduleAssociationType {
    P("Passenger use"),
    O("Operating use only");

    private final String description;

    ScheduleAssociationType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
