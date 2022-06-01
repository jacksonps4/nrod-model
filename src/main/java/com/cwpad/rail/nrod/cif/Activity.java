package com.cwpad.rail.nrod.cif;


public enum Activity {
    A("Stops or shunts for other trains to pass"),
    AE("Attach/detach assisting locomotive"),
    AX("Shows as 'X' on arrival"),
    BL("Stops for banking locomotive"),
    C("Stops to change trainmen"),
    D("Stops to set down passengers"),
    DX("Shows as 'X' on departure (assumed)"), // assumed
    _D("Stops to detach vehicles"),
    E("Stops for examination"),
    G("National Rail Timetable data to add"),
    H("Notional activity to prevent WTT timing columns merge"),
    HH("As H, where a third column is involved"),
    K("Passenger count point"),
    KC("Ticket collection and examination point"),
    KE("Ticket examination point"),
    KF("Ticket Examination Point, 1st Class only"),
    KS("Selective Ticket Examination Point"),
    L("Stops to change locomotives"),
    N("Stop not advertised"),
    OP("Stops for other operating reasons"),
    OR("Train Locomotive on rear"),
    PR("Propelling between points shown"),
    R("Stops when required"),
    RM("Reversing movement, or driver changes ends"),
    RR("Stops for locomotive to run round train"),
    S("Stops for railway personnel only"),
    T("Stops to take up and set down passengers"),
    _T("Stops to attach and detach vehicles"),
    TB("Train begins (Origin)"),
    TF("Train finishes (Destination)"),
    TS("Detail Consist for TOPS Direct"),
    TW("Stops (or at pass) for tablet, staff or token."),
    U("Stops to take up passengers"),
    UX("Stops to pick up Red Star parcels"),
    _U("Stops to attach vehicles"),
    VV("Do not show diversion (not sent to TSDB)"),
    W("Stops for watering of coaches"),
    X("Passes another train at crossing point on single line");

    private final String description;

    Activity(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static Activity parse(String activity) {
        if (activity.startsWith("-")) {
            return valueOf("_" + activity.substring(1));
        }
        return Activity.valueOf(activity);
    }
}
