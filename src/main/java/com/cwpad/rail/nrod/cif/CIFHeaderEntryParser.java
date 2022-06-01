package com.cwpad.rail.nrod.cif;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

class CIFHeaderEntryParser extends CIFBaseEntryParser {
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("ddMMyy");
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");

    @Override
    public CIFHeaderRecord parse(String recordData) {
        checkType(CIFRecordType.HD, recordData);
        CIFHeaderRecord record = new CIFHeaderRecord();
        record.setFileMainframeIdentity(recordData.substring(2, 22));
        record.setDateOfExtract(parseDate(recordData.substring(22, 28)));
        record.setTimeOfExtract(parseTime(recordData.substring(28, 32)));
        record.setCurrentFileRef(recordData.substring(32, 39));
        record.setLastFileRef(recordData.substring(39, 46).trim());
        record.setUpdateType(parseUpdateType(recordData.substring(46, 47)));
        record.setVersion(recordData.substring(47, 48));
        record.setUserExtractStartDate(parseDate(recordData.substring(48, 54)));
        record.setUserExtractEndDate(parseDate(recordData.substring(54, 60)));
        return record;
    }

    protected final LocalDate parseDate(String date) {
        return LocalDate.from(dateFormatter.parse(date));
    }

    protected final LocalTime parseTime(String time) {
        return LocalTime.from(timeFormatter.parse(time));
    }

    private CIFHeaderRecord.UpdateType parseUpdateType(String updateType) {
        switch (updateType) {
            case "U":
                return CIFHeaderRecord.UpdateType.UPDATE;
            case "F":
                return CIFHeaderRecord.UpdateType.FULL;
        }
        throw new IllegalArgumentException("Invalid update type: " + updateType);
    }
}
