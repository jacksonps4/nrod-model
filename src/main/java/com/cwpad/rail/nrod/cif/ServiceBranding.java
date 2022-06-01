package com.cwpad.rail.nrod.cif;


public enum ServiceBranding {
    E("Eurostar"), U("Unknown");

    private final String description;

    ServiceBranding(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
