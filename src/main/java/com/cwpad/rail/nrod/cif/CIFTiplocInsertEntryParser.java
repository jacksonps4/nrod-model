package com.cwpad.rail.nrod.cif;

class CIFTiplocInsertEntryParser extends CIFTiplocEntryParser {
    @Override
    public CIFTiplocInsertRecord parse(String recordData) {
        checkType(CIFRecordType.TI, recordData);

        CIFTiplocInsertRecord record = new CIFTiplocInsertRecord();
        parseTiplocRecord(recordData, record);
        return record;
    }
}
