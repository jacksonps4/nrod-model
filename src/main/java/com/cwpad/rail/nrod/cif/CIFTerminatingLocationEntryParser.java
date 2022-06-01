package com.cwpad.rail.nrod.cif;

class CIFTerminatingLocationEntryParser extends CIFBaseEntryParser {
    private final CIFTimeParser cifTimeParser = new CIFTimeParser();
    private final CIFActivityParser cifActivityParser = new CIFActivityParser();

    @Override
    public CIFTerminatingLocationRecord parse(String recordData) {
        checkType(CIFRecordType.LT, recordData);

        CIFTerminatingLocationRecord record = new CIFTerminatingLocationRecord();
        record.setTiploc(recordData.substring(2, 9).trim());
        record.setScheduledArrival(cifTimeParser.parseTime(recordData.substring(10, 15)));
        record.setPublicArrival(cifTimeParser.parseTime(recordData.substring(15, 19)));

        String platform = recordData.substring(19, 22).trim();
        if (platform.length() > 0) {
            record.setPlatform(platform);
        }
        String path = recordData.substring(22, 25).trim();
        if (path.length() > 0) {
            record.setPath(path);
        }

        cifActivityParser.parse(recordData.substring(25, 37)).stream()
                .forEach(record::addActivity);

        return record;
    }
}
