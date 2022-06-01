package com.cwpad.rail.nrod.cif;

class CIFIntermediateLocationEntryParser extends CIFBaseEntryParser {
    private final CIFTimeParser timeParser = new CIFTimeParser();
    private final CIFDurationParser durationParser = new CIFDurationParser();
    private final CIFActivityParser cifActivityParser = new CIFActivityParser();

    @Override
    public CIFIntermediateLocationRecord parse(String recordData) {
        checkType(CIFRecordType.LI, recordData);

        CIFIntermediateLocationRecord record = new CIFIntermediateLocationRecord();
        record.setTiploc(recordData.substring(2, 9).trim().intern());

        String scheduledArrival = recordData.substring(10, 15).trim();
        if (scheduledArrival.length() > 0) {
            record.setScheduledArrival(timeParser.parseTime(scheduledArrival));
        }
        String scheduledDeparture = recordData.substring(15, 20).trim();
        if (scheduledDeparture.length() > 0) {
            record.setScheduledDeparture(timeParser.parseTime(scheduledDeparture));
        }
        String scheduledPass = recordData.substring(20, 25).trim();
        if (scheduledPass.length() > 0) {
            record.setScheduledPass(timeParser.parseTime(scheduledPass));
        }

        String publicArrival = recordData.substring(25, 29).trim();
        if (publicArrival.length() > 0) {
            record.setPublicArrival(timeParser.parseTime(publicArrival));
        }
        String publicDeparture = recordData.substring(29, 33).trim();
        if (publicDeparture.length() > 0) {
            record.setPublicDeparture(timeParser.parseTime(publicDeparture));
        }

        String platform = recordData.substring(33, 36).trim();
        if (platform.length() > 0) {
            record.setPlatform(platform.intern());
        }
        String line = recordData.substring(36, 39).trim();
        if (line.length() > 0) {
            record.setLine(line.intern());
        }
        String path = recordData.substring(39, 42).trim();
        if (path.length() > 0) {
            record.setPath(path.intern());
        }

        cifActivityParser.parse(recordData.substring(42, 54)).stream()
                .forEach(record::addActivity);

        String engineeringAllowance = recordData.substring(54, 56).trim();
        if (engineeringAllowance.length() > 0) {
            record.setEngineeringAllowance(durationParser.parse(engineeringAllowance));
        }

        String pathingAllowance = recordData.substring(56, 58).trim();
        if (pathingAllowance.length() > 0) {
            record.setPathingAllowance(durationParser.parse(pathingAllowance));
        }

        String performanceAllowance = recordData.substring(58, 60).trim();
        if (performanceAllowance.length() > 0) {
            record.setPerformanceAllowance(durationParser.parse(performanceAllowance));
        }

        return record;
    }
}
