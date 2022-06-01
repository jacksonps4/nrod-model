package com.cwpad.rail.nrod.cif;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

class CIFAssociationRecord extends CIFRecord {
    private CIFTransactionType transactionType;
    private String mainTrainUid;
    private String associatedTrainUid;
    private LocalDate associationStartDate;
    private LocalDate associationEndDate;
    private Set<DayOfWeek> associationDays;
    private ScheduleAssociationCategory associationCategory;
    private ScheduleAssociationDateIndicator scheduleAssociationDateIndicator;
    private String tiploc;
    private char baseLocationSuffix;
    private char associatedLocationSuffix;
    private ScheduleAssociationType associationType;
    private StpIndicator stpIndicator;

    public CIFAssociationRecord() {
        super(CIFRecordType.AA);
    }

    public CIFTransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(CIFTransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getMainTrainUid() {
        return mainTrainUid;
    }

    public void setMainTrainUid(String mainTrainUid) {
        this.mainTrainUid = mainTrainUid;
    }

    public String getAssociatedTrainUid() {
        return associatedTrainUid;
    }

    public void setAssociatedTrainUid(String associatedTrainUid) {
        this.associatedTrainUid = associatedTrainUid;
    }

    public LocalDate getAssociationStartDate() {
        return associationStartDate;
    }

    public void setAssociationStartDate(LocalDate associationStartDate) {
        this.associationStartDate = associationStartDate;
    }

    public LocalDate getAssociationEndDate() {
        return associationEndDate;
    }

    public void setAssociationEndDate(LocalDate associationEndDate) {
        this.associationEndDate = associationEndDate;
    }

    public Set<DayOfWeek> getAssociationDays() {
        return associationDays;
    }

    public void setAssociationDays(Set<DayOfWeek> associationDays) {
        this.associationDays = associationDays;
    }

    public ScheduleAssociationCategory getAssociationCategory() {
        return associationCategory;
    }

    public void setAssociationCategory(ScheduleAssociationCategory associationCategory) {
        this.associationCategory = associationCategory;
    }

    public ScheduleAssociationDateIndicator getScheduleAssociationDateIndicator() {
        return scheduleAssociationDateIndicator;
    }

    public void setScheduleAssociationDateIndicator(ScheduleAssociationDateIndicator scheduleAssociationDateIndicator) {
        this.scheduleAssociationDateIndicator = scheduleAssociationDateIndicator;
    }

    public String getTiploc() {
        return tiploc;
    }

    public void setTiploc(String tiploc) {
        this.tiploc = tiploc;
    }

    public char getBaseLocationSuffix() {
        return baseLocationSuffix;
    }

    public void setBaseLocationSuffix(char baseLocationSuffix) {
        this.baseLocationSuffix = baseLocationSuffix;
    }

    public char getAssociatedLocationSuffix() {
        return associatedLocationSuffix;
    }

    public void setAssociatedLocationSuffix(char associatedLocationSuffix) {
        this.associatedLocationSuffix = associatedLocationSuffix;
    }

    public ScheduleAssociationType getAssociationType() {
        return associationType;
    }

    public void setAssociationType(ScheduleAssociationType associationType) {
        this.associationType = associationType;
    }

    public StpIndicator getStpIndicator() {
        return stpIndicator;
    }

    public void setStpIndicator(StpIndicator stpIndicator) {
        this.stpIndicator = stpIndicator;
    }
}
