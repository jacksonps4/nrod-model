package com.cwpad.rail.nrod.cif;

public enum Catering {
    C("Buffet Service"),
    F("Restaurant Car available for First Class passengers"),
    H("Service of hot food available"),
    M("Meal included for First Class passengers"),
    R("Restaurant"),
    T("Trolley Service");

    private final String description;

    Catering(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
