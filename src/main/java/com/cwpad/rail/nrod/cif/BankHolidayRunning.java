package com.cwpad.rail.nrod.cif;

public enum BankHolidayRunning {
    X("Does not run on specified Bank Holiday Mondays"),
    E("Does not run on specified Edinburgh Holiday dates (no longer used)"),
    G("Does not run on specified Glasgow Holiday dates.");

    private final String description;

    BankHolidayRunning(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
