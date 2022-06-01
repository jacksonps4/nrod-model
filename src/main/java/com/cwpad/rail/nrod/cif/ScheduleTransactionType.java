package com.cwpad.rail.nrod.cif;

/**
 * Created by chrisw on 25/11/2015.
 */
public enum ScheduleTransactionType {
    CANCELLATION, OVERLAY, SHORT_TERM, PERMANENT;

    public static ScheduleTransactionType parse(String cifStpIndicator) {
        switch (cifStpIndicator) {
            case "C":
                return CANCELLATION;
            case "O":
                return OVERLAY;
            case "N":
                return SHORT_TERM;
            case "P":
                return PERMANENT;
            default:
                throw new IllegalArgumentException(cifStpIndicator);
        }
    }
}
