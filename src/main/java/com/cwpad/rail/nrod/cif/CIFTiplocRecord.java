package com.cwpad.rail.nrod.cif;

import java.util.Optional;

abstract class CIFTiplocRecord extends CIFRecord {
    private String tiploc;
    private String capitalsIdentification;
    private int nationalLocationCode;
    private Optional<Character> nlcCheckCharacter;
    private Optional<String> tpsDescription;
    private Optional<Integer> stanox;
    private Optional<Integer> postOfficeLocationCode;
    private Optional<String> crsCode;
    private Optional<String> description;

    public CIFTiplocRecord(CIFRecordType cifRecordType) {
        super(cifRecordType);
    }

    public String getTiploc() {
        return tiploc;
    }

    public void setTiploc(String tiploc) {
        this.tiploc = tiploc;
    }

    public String getCapitalsIdentification() {
        return capitalsIdentification;
    }

    public void setCapitalsIdentification(String capitalsIdentification) {
        this.capitalsIdentification = capitalsIdentification;
    }

    public int getNationalLocationCode() {
        return nationalLocationCode;
    }

    public void setNationalLocationCode(int nationalLocationCode) {
        this.nationalLocationCode = nationalLocationCode;
    }

    public Optional<Character> getNlcCheckCharacter() {
        return nlcCheckCharacter;
    }

    public void setNlcCheckCharacter(Optional<Character> nlcCheckCharacter) {
        this.nlcCheckCharacter = nlcCheckCharacter;
    }

    public Optional<String> getTpsDescription() {
        return tpsDescription;
    }

    public void setTpsDescription(Optional<String> tpsDescription) {
        this.tpsDescription = tpsDescription;
    }

    public Optional<Integer> getStanox() {
        return stanox;
    }

    public void setStanox(Optional<Integer> stanox) {
        this.stanox = stanox;
    }

    public Optional<Integer> getPostOfficeLocationCode() {
        return postOfficeLocationCode;
    }

    public void setPostOfficeLocationCode(Optional<Integer> postOfficeLocationCode) {
        this.postOfficeLocationCode = postOfficeLocationCode;
    }

    public Optional<String> getCrsCode() {
        return crsCode;
    }

    public void setCrsCode(Optional<String> crsCode) {
        this.crsCode = crsCode;
    }

    public Optional<String> getDescription() {
        return description;
    }

    public void setDescription(Optional<String> description) {
        this.description = description;
    }
}
