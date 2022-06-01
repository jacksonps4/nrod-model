package com.cwpad.rail.nrod.cif;

public enum ScheduleAssociationDateIndicator {
    S("standard (same day)"), N("over-next-midnight"), P("over-previous-midnight");

    private final String description;

    ScheduleAssociationDateIndicator(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
