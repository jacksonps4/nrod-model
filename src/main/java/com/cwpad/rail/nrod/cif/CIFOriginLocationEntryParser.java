package com.cwpad.rail.nrod.cif;


class CIFOriginLocationEntryParser extends CIFBaseEntryParser {
    private final CIFTimeParser cifTimeParser = new CIFTimeParser();
    private final CIFDurationParser cifDurationParser = new CIFDurationParser();
    private final CIFActivityParser cifActivityParser = new CIFActivityParser();

    @Override
    public CIFOriginLocationRecord parse(String recordData) {
        checkType(CIFRecordType.LO, recordData);

        CIFOriginLocationRecord record = new CIFOriginLocationRecord();
        record.setTiploc(recordData.substring(2, 9).trim().intern());

        record.setScheduledDeparture(cifTimeParser.parseTime(recordData.substring(10, 15)));
        record.setPublicDeparture(cifTimeParser.parseTime(recordData.substring(15, 19)));

        String platform = recordData.substring(19, 22).trim();
        if (platform.length() > 0) {
            record.setPlatform(platform);
        }

        String line = recordData.substring(22, 25).trim();
        if (line.length() > 0) {
            record.setLine(line);
        }

        String engineeringAllowance = recordData.substring(25, 27).trim();
        if (engineeringAllowance.length() > 0) {
            record.setEngineeringAllowance(cifDurationParser.parse(engineeringAllowance));
        }

        String pathingAllowance = recordData.substring(27, 29).trim();
        if (pathingAllowance.length() > 0) {
            record.setPathingAllowance(cifDurationParser.parse(pathingAllowance));
        }

        cifActivityParser.parse(recordData.substring(29, 41)).stream()
                .forEach(record::addActivity);

        return record;
    }
}
