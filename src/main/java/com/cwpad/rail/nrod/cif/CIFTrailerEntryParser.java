package com.cwpad.rail.nrod.cif;

class CIFTrailerEntryParser extends CIFBaseEntryParser {
    @Override
    public CIFTrailerRecord parse(String recordData) {
        checkType(CIFRecordType.ZZ, recordData);
        return new CIFTrailerRecord();
    }
}
