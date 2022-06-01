package com.cwpad.rail.nrod.cif;


enum CIFRecordType {
    HD(new CIFHeaderEntryParser()), BS(new CIFBasicScheduleEntryParser()),
    BX(new CIFBasicScheduleExtraDetailsEntryParser()),
    LO(new CIFOriginLocationEntryParser()),
    LI(new CIFIntermediateLocationEntryParser()),
    CR(new CIFChangesEnRouteEntryParser()),
    LT(new CIFTerminatingLocationEntryParser()),
    AA(new CIFAssociationEntryParser()),
    TI(new CIFTiplocInsertEntryParser()),
    TA(new CIFTiplocAmendEntryParser()),
    TD(new CIFTiplocDeleteEntryParser()),
    ZZ(new CIFTrailerEntryParser());

    private final CIFEntryParser parser;

    CIFRecordType(CIFEntryParser parser) {
        this.parser = parser;
    }

    public CIFEntryParser getParser() {
        return parser;
    }
}
