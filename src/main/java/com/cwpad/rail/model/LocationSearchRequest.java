package com.cwpad.rail.model;

public class LocationSearchRequest {
    private String term;

    public LocationSearchRequest() {
    }

    public LocationSearchRequest(String term) {
        this.term = term;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
