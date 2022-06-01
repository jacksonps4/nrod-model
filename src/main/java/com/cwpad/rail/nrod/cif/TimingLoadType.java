package com.cwpad.rail.nrod.cif;

public enum TimingLoadType {
    A("Class 14x series 2-axle"),
    E("Class 158, 168, 170, 172, 175"),
    N("Class 165"),
    S("Class 150, 153, 155 or 156"),
    T("Class 165/1 or 166"),
    V("Class 220/221; Bombardier DMU"),
    X("Class 159"),
    D1("Power car + trailer"),
    D2("2 power cars + trailer"),
    D3("Power twin"),
    EMU("EMU class"),
    HAULED("Hauled train");

    private final String description;

    TimingLoadType(String description) {
        this.description = description;
    }
}
