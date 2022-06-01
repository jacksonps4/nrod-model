package com.cwpad.rail.nrod.cif;


class CIFBasicScheduleExtraDetailsRecord extends CIFRecord {
    private String uicCode;
    private String atocCode;
    private boolean applicable;

    public CIFBasicScheduleExtraDetailsRecord() {
        super(CIFRecordType.BX);
    }

    public String getUicCode() {
        return uicCode;
    }

    public void setUicCode(String uicCode) {
        this.uicCode = uicCode;
    }

    public String getAtocCode() {
        return atocCode;
    }

    public void setAtocCode(String atocCode) {
        this.atocCode = atocCode;
    }

    public boolean isApplicable() {
        return applicable;
    }

    public void setApplicable(boolean applicable) {
        this.applicable = applicable;
    }
}
