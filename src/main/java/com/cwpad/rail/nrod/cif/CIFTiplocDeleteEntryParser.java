package com.cwpad.rail.nrod.cif;

class CIFTiplocDeleteEntryParser extends CIFBaseEntryParser {
    @Override
    public CIFTiplocDeleteRecord parse(String recordData) {
        checkType(CIFRecordType.TD, recordData);

        CIFTiplocDeleteRecord record = new CIFTiplocDeleteRecord();
        record.setTiploc(recordData.substring(2, 9).trim());
        return record;
    }
}
