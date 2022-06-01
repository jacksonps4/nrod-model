package com.cwpad.rail.nrod.cif;

public enum TrainStatus {
    B("Bus (Permanent)"),
    F("Freight (Permanent - WTT)"),
    P("Passenger & Parcels (Permanent - WTT)"),
    S("Ship (Permanent)"),
    T("Trip (Permanent)"),
    _1("STP Passenger & Parcels"),
    _2("STP Freight"),
    _3("STP Trip"),
    _4("STP Ship"),
    _5("STP Bus");

    private final String status;

    TrainStatus(String status) {
        this.status = status;
    }

    public static TrainStatus parse(char trainStatus) {
        if (Character.isDigit(trainStatus)) {
            return TrainStatus.valueOf("_" + trainStatus);
        } else {
            if (!Character.isWhitespace(trainStatus)) {
                return TrainStatus.valueOf(String.valueOf(trainStatus));
            } else {
                return null;
            }
        }
    }

    @Override
    public String toString() {
        return status;
    }
}
