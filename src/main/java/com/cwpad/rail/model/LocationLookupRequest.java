package com.cwpad.rail.model;

public class LocationLookupRequest {
    private String tiploc;
    private String crsCode;
    private String identifier;
    private String searchTerm;
    private Integer stanox;

    public static LocationLookupRequest withTiploc(String tiploc) {
        LocationLookupRequest request = new LocationLookupRequest();
        request.setTiploc(tiploc);
        return request;
    }

    public static LocationLookupRequest withCrsCode(String crsCode) {
        LocationLookupRequest request = new LocationLookupRequest();
        request.setCrsCode(crsCode);
        return request;
    }

    public static LocationLookupRequest withIdentifier(String identifier) {
        LocationLookupRequest request = new LocationLookupRequest();
        request.setIdentifier(identifier);
        return request;
    }

    public static LocationLookupRequest withStanox(int stanox) {
        LocationLookupRequest request = new LocationLookupRequest();
        request.setStanox(stanox);
        return request;
    }

    public static LocationLookupRequest withSearchTerm(String searchTerm) {
        LocationLookupRequest request = new LocationLookupRequest();
        request.setSearchTerm(searchTerm);
        return request;
    }

    public String getTiploc() {
        return tiploc;
    }

    public void setTiploc(String tiploc) {
        this.tiploc = tiploc;
    }

    public String getCrsCode() {
        return crsCode;
    }

    public void setCrsCode(String crsCode) {
        this.crsCode = crsCode;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String requestIdentifier() {
        if (crsCode != null) {
            return crsCode;
        } else if (tiploc != null) {
            return tiploc;
        } else if (stanox != null) {
            return String.valueOf(stanox);
        } else if (identifier != null) {
            return identifier;
        } else {
            return searchTerm;
        }
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public Integer getStanox() {
        return stanox;
    }

    public void setStanox(Integer stanox) {
        this.stanox = stanox;
    }
}
