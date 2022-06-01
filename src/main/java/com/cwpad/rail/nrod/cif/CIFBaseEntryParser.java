package com.cwpad.rail.nrod.cif;

abstract class CIFBaseEntryParser implements CIFEntryParser {
    protected final void checkType(CIFRecordType recordType, String recordData) {
        String type = recordData.substring(0, 2);
        String expectedType = recordType.toString();
        if (!expectedType.equals(type)) {
            throw new IllegalArgumentException("This parser can bootstrap " + expectedType + " records only");
        }
    }
}
