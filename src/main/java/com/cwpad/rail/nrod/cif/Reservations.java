package com.cwpad.rail.nrod.cif;


public enum Reservations {
    A("Seat Reservations Compulsory (R symbol in white box)"),
    E("Reservations for Bicycles Essential (Inverted black triangle)"),
    R("Seat Reservations Recommended (R symbol in black box)"),
    S("Seat Reservations possible from any station (white diamond symbol)");

    private final String description;

    Reservations(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
