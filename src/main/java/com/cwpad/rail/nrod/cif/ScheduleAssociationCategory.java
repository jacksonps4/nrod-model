package com.cwpad.rail.nrod.cif;

public enum ScheduleAssociationCategory {
    JJ("Join"), VV("Divide"), NP("Next");

    private final String description;

    ScheduleAssociationCategory(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
