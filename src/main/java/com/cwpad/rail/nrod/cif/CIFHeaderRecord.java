package com.cwpad.rail.nrod.cif;

import java.time.LocalDate;
import java.time.LocalTime;

class CIFHeaderRecord extends CIFRecord {
    enum UpdateType {
        UPDATE, FULL
    }

    private String fileMainframeIdentity;
    private LocalDate dateOfExtract;
    private LocalTime timeOfExtract;
    private String currentFileRef;
    private String lastFileRef;
    private UpdateType updateType;
    private String version;
    private LocalDate userExtractStartDate;
    private LocalDate userExtractEndDate;

    public CIFHeaderRecord() {
        super(CIFRecordType.HD);
    }

    public String getFileMainframeIdentity() {
        return fileMainframeIdentity;
    }

    public void setFileMainframeIdentity(String fileMainframeIdentity) {
        this.fileMainframeIdentity = fileMainframeIdentity;
    }

    public LocalDate getDateOfExtract() {
        return dateOfExtract;
    }

    public void setDateOfExtract(LocalDate dateOfExtract) {
        this.dateOfExtract = dateOfExtract;
    }

    public LocalTime getTimeOfExtract() {
        return timeOfExtract;
    }

    public void setTimeOfExtract(LocalTime timeOfExtract) {
        this.timeOfExtract = timeOfExtract;
    }

    public String getCurrentFileRef() {
        return currentFileRef;
    }

    public void setCurrentFileRef(String currentFileRef) {
        this.currentFileRef = currentFileRef;
    }

    public String getLastFileRef() {
        return lastFileRef;
    }

    public void setLastFileRef(String lastFileRef) {
        this.lastFileRef = lastFileRef;
    }

    public UpdateType getUpdateType() {
        return updateType;
    }

    public void setUpdateType(UpdateType updateType) {
        this.updateType = updateType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public LocalDate getUserExtractStartDate() {
        return userExtractStartDate;
    }

    public void setUserExtractStartDate(LocalDate userExtractStartDate) {
        this.userExtractStartDate = userExtractStartDate;
    }

    public LocalDate getUserExtractEndDate() {
        return userExtractEndDate;
    }

    public void setUserExtractEndDate(LocalDate userExtractEndDate) {
        this.userExtractEndDate = userExtractEndDate;
    }
}
