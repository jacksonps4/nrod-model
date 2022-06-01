package com.cwpad.rail.nrod.cif;


class CIFRecord {
    private final CIFRecordType cifRecordType;

    public CIFRecord(CIFRecordType cifRecordType) {
        this.cifRecordType = cifRecordType;
    }

    public CIFRecordType getCifRecordType() {
        return cifRecordType;
    }
}
