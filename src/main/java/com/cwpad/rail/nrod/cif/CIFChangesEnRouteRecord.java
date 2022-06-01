package com.cwpad.rail.nrod.cif;

class CIFChangesEnRouteRecord extends CIFTrainCharacteristics {
    private String tiploc;
    private String uicCode;

    public CIFChangesEnRouteRecord() {
        super(CIFRecordType.CR);
    }

    public String getTiploc() {
        return tiploc;
    }

    public void setTiploc(String tiploc) {
        this.tiploc = tiploc;
    }

    public String getUicCode() {
        return uicCode;
    }

    public void setUicCode(String uicCode) {
        this.uicCode = uicCode;
    }
}
