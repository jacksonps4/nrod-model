package com.cwpad.rail.nrod.cif;

public enum Sleepers {
    B("First & Standard Class"),
    F("First Class only"),
    S("Standard Class only");

    private final String description;

    Sleepers(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
