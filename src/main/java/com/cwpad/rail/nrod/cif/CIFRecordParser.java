package com.cwpad.rail.nrod.cif;

class CIFRecordParser {
    private final String record;

    public CIFRecordParser(String record) {
        this.record = record;
    }

    public CIFRecord readEntry() {
        String recordType = record.substring(0, 2);
        for (CIFRecordType type : CIFRecordType.values()) {
            if (type.toString().equals(recordType)) {
                return type.getParser().parse(this.record);
            }
        }

        throw new IllegalArgumentException(String.format("Unparseable record: %s", record));
    }
}
