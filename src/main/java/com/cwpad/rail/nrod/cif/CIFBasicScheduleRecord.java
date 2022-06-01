package com.cwpad.rail.nrod.cif;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

class CIFBasicScheduleRecord extends CIFTrainCharacteristics {
    private CIFTransactionType cifTransactionType;
    private String trainUid;
    private LocalDate runsFrom;
    private LocalDate runsTo;
    private Set<DayOfWeek> daysRun;
    private BankHolidayRunning bankHolidayRunning;
    private TrainStatus trainStatus;
    private StpIndicator stpIndicator;

    public CIFBasicScheduleRecord() {
        super(CIFRecordType.BS);
    }

    public CIFTransactionType getCifTransactionType() {
        return cifTransactionType;
    }

    public void setCifTransactionType(CIFTransactionType cifTransactionType) {
        this.cifTransactionType = cifTransactionType;
    }

    public String getTrainUid() {
        return trainUid;
    }

    public void setTrainUid(String trainUid) {
        this.trainUid = trainUid;
    }

    public LocalDate getRunsFrom() {
        return runsFrom;
    }

    public void setRunsFrom(LocalDate runsFrom) {
        this.runsFrom = runsFrom;
    }

    public LocalDate getRunsTo() {
        return runsTo;
    }

    public void setRunsTo(LocalDate runsTo) {
        this.runsTo = runsTo;
    }

    public Set<DayOfWeek> getDaysRun() {
        return daysRun;
    }

    public void setDaysRun(Set<DayOfWeek> daysRun) {
        this.daysRun = daysRun;
    }

    public BankHolidayRunning getBankHolidayRunning() {
        return bankHolidayRunning;
    }

    public void setBankHolidayRunning(BankHolidayRunning bankHolidayRunning) {
        this.bankHolidayRunning = bankHolidayRunning;
    }

    public TrainStatus getTrainStatus() {
        return trainStatus;
    }

    public void setTrainStatus(TrainStatus trainStatus) {
        this.trainStatus = trainStatus;
    }

    public StpIndicator getStpIndicator() {
        return stpIndicator;
    }

    public void setStpIndicator(StpIndicator stpIndicator) {
        this.stpIndicator = stpIndicator;
    }
}
