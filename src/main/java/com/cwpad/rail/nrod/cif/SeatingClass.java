package com.cwpad.rail.nrod.cif;

public enum SeatingClass {
    B("First & Standard seats"),
    S("Standard class only");

    private final String description;

    SeatingClass(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
