package com.cwpad.rail.nrod.cif;

public enum StpIndicator {
    C("STP Cancellation of Permanent schedule"),
    N("New STP schedule (not an overlay)"),
    O("STP overlay of Permanent schedule"),
    P("Permanent");

    private final String description;

    StpIndicator(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
