package com.cwpad.rail.nrod.cif;

class CIFBasicScheduleExtraDetailsEntryParser extends CIFBaseEntryParser {
    @Override
    public CIFBasicScheduleExtraDetailsRecord parse(String recordData) {
        checkType(CIFRecordType.BX, recordData);

        CIFBasicScheduleExtraDetailsRecord record = new CIFBasicScheduleExtraDetailsRecord();
        String uicCode = recordData.substring(6, 11).trim();
        if (uicCode.length() > 0) {
            record.setUicCode(uicCode);
        }

        record.setAtocCode(recordData.substring(11, 13));
        char applicableValue = recordData.charAt(13);
        switch (applicableValue) {
            case 'Y':
                record.setApplicable(true);
                break;
            case 'N':
                record.setApplicable(false);
                break;
            default:
                throw new IllegalArgumentException();
        }

        return record;
    }
}
