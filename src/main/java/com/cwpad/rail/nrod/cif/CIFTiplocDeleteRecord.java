package com.cwpad.rail.nrod.cif;

class CIFTiplocDeleteRecord extends CIFRecord {
    private String tiploc;

    public CIFTiplocDeleteRecord() {
        super(CIFRecordType.TD);
    }

    public String getTiploc() {
        return tiploc;
    }

    public void setTiploc(String tiploc) {
        this.tiploc = tiploc;
    }
}
