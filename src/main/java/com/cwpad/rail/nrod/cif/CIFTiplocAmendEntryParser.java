package com.cwpad.rail.nrod.cif;

class CIFTiplocAmendEntryParser extends CIFTiplocEntryParser {
    @Override
    public CIFTiplocAmendRecord parse(String recordData) {
        checkType(CIFRecordType.TA, recordData);

        CIFTiplocAmendRecord record = new CIFTiplocAmendRecord();
        parseTiplocRecord(recordData, record);

        String newTiploc = recordData.substring(72, 79).trim();
        if (newTiploc.length() > 0) {
            record.setNewTiploc(newTiploc);
        }
        return record;
    }
}
