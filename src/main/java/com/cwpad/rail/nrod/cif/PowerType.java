package com.cwpad.rail.nrod.cif;

public enum PowerType {
    D("Diesel"), DEM("Diesel Electric Multiple Unit"), DMU("Diesel Mechanical Multiple Unit"),
    E("Electric"), ED("Electro-Diesel"), EML("EMU plus D, E, ED locomotive"),
    EMU("Electric Multiple Unit"), HST("High Speed Train");

    private final String description;

    PowerType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
