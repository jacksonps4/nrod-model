package com.cwpad.rail.nrod.cif;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class CIFAssociationEntryParser extends CIFBaseEntryParser {
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyMMdd");
    private final CIFDaysRunParser daysRunParser = new CIFDaysRunParser();

    @Override
    public CIFAssociationRecord parse(String recordData) {
        checkType(CIFRecordType.AA, recordData);

        CIFAssociationRecord record = new CIFAssociationRecord();
        record.setTransactionType(CIFTransactionType.parse(recordData.substring(2, 3)));
        record.setMainTrainUid(recordData.substring(3, 9));
        record.setAssociatedTrainUid(recordData.substring(9, 15));
        record.setAssociationStartDate(LocalDate.from(dateFormatter.parse(recordData.substring(15, 21))));
        record.setAssociationEndDate(LocalDate.from(dateFormatter.parse(recordData.substring(21, 27))));
        record.setAssociationDays(daysRunParser.parse(recordData.substring(27, 34)));

        String assocationCategory = recordData.substring(34, 36).trim();
        if (assocationCategory.length() > 0) {
            record.setAssociationCategory(ScheduleAssociationCategory.valueOf(assocationCategory));
        }

        String associationDateIndicator = recordData.substring(36, 37).trim();
        if (assocationCategory.length() > 0) {
            record.setScheduleAssociationDateIndicator(
                    ScheduleAssociationDateIndicator.valueOf(associationDateIndicator));
        }

        record.setTiploc(recordData.substring(37, 44).trim());
        record.setBaseLocationSuffix(recordData.substring(44, 45).charAt(0));
        record.setAssociatedLocationSuffix(recordData.substring(45, 46).charAt(0));

        String associationType = recordData.substring(47, 48).trim();
        if (associationType.length() > 0) {
            record.setAssociationType(ScheduleAssociationType.valueOf(associationType));
        }

        record.setStpIndicator(StpIndicator.valueOf(recordData.substring(79, 80)));

        return record;
    }
}
