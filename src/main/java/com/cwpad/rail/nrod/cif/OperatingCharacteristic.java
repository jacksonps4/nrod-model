package com.cwpad.rail.nrod.cif;

public enum OperatingCharacteristic {
    B("Vacuum Braked"),
    C("Timed at 100 m.p.h."),
    D("Driver Only Operation (Coaching stock trains)"),
    E("Conveys Mark 4 Coaches"),
    G("Trainman (Guard) required"),
    M("Timed at 110 m.p.h."),
    P("Push/Pull train"),
    Q("Runs as required"),
    R("Air conditioned with PA system"),
    S("Steam Heated"),
    Y("Runs to Terminals/Yards as required"),
    Z("May convey traffic to SB1C gauge. Not to be diverted from booked route without authority");

    private final String description;

    OperatingCharacteristic(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
