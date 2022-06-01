package com.cwpad.rail.nrod.cif;

public enum TrainCategory {
    OL("London Underground/Metro Service"),
    OU("Unadvertised Ordinary Passenger"),
    OO("Ordinary Passenger"),
    OS("Staff Train"),
    OW("Mixed"),
    XC("Channel Tunnel"),
    XD("Sleeper (Europe Night Services)"),
    XI("International"),
    XR("Motorail"),
    XU("Unadvertised Express"),
    XX("Express Passenger"),
    XZ("Sleeper (Domestic)"),
    BR("Bus – Replacement due to engineering work"),
    BS("Bus – WTT Service"),
    SS("Ship"),
    EE("Empty Coaching Stock (ECS)"),
    EL("ECS, London Underground/Metro Service"),
    ES("ECS & Staff"),
    JJ("Postal"),
    PM("Post Office Controlled Parcels"),
    PP("Parcels"),
    PV("Empty NPCCS"),
    DD("Departmental"),
    DH("Civil Engineer"),
    DI("Mechanical & Electrical Engineer"),
    DQ("Stores"),
    DT("Test"),
    DY("Signal & Telecommunications Engineer"),
    ZB("Locomotive & Brake Van"),
    ZZ("Light Locomotive"),
    J2("RfD Automotive (Components)"),
    H2("RfD Automotive (Vehicles)"),
    J6("RfD Building Materials (UK Contracts)"),
    J5("RfD Chemicals (UK Contracts)"),
    J3("RfD Edible Products (UK Contracts)"),
    J9("RfD Freightliner (Contracts)"),
    H9("RfD Freightliner (Other)"),
    H8("RfD European"),
    J8("RfD General Merchandise (UK Contracts)"),
    J4("RfD Industrial Minerals (UK Contracts)"),
    A0("Coal (Distributive)"),
    E0("Coal (Electricity) MGR"),
    B0("Coal (Other) and Nuclear"),
    B1("Metals"),
    B4("Aggregates"),
    B5("Domestic and Industrial Waste"),
    B6("Building Materials (TLF)"),
    B7("Petroleum Products"),
    H0("RfD European Channel Tunnel (Mixed Business)"),
    H1("RfD European Channel Tunnel Intermodal"),
    H3("RfD European Channel Tunnel Automotive"),
    H4("RfD European Channel Tunnel Contract Services"),
    H5("RfD European Channel Tunnel Haulmark"),
    H6("RfD European Channel Tunnel Joint Venture");

    private final String description;

    TrainCategory(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String category() {
        String constant = toString();
        switch (constant.charAt(0)) {
            case 'O':
                return "Ordinary Passenger Trains";
            case 'X':
                return "Express Passenger Trains";
            case 'A':
            case 'B':
                if (Character.isDigit(constant.charAt(1))) {
                    return "Trainload Freight";
                }
                return "Bus";
            case 'S':
                return "Ship";
            case 'E':
                if (Character.isDigit(constant.charAt(1))) {
                    return "Trainload Freight";
                }
                return "Empty Coaching Stock Trains";
            case 'H':
                int v = Integer.parseInt(constant.substring(1, 2));
                switch (v) {
                    case 2:
                    case 8:
                    case 9:
                        return "Railfreight Distribution";
                    default:
                        return "Railfreight Distribution (Channel Tunnel)";
                }
            case 'J':
                if (constant.equals("JJ")) {
                    return "Parcels and Postal Trains";
                } else {
                    return "Railfreight Distribution";
                }
            case 'P':
                return "Parcels and Postal Trains";
            case 'D':
                return "Departmental Trains";
            case 'Z':
                return "Light Locomotives";
        }
        throw new IllegalArgumentException();
    }
}
