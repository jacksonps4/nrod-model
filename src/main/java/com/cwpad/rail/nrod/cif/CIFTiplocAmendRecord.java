package com.cwpad.rail.nrod.cif;

class CIFTiplocAmendRecord extends CIFTiplocRecord {
    private String newTiploc;

    public CIFTiplocAmendRecord() {
        super(CIFRecordType.TA);
    }

    public String getNewTiploc() {
        return newTiploc;
    }

    public void setNewTiploc(String newTiploc) {
        this.newTiploc = newTiploc;
    }
}
