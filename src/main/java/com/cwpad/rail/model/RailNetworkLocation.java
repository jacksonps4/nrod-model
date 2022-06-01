package com.cwpad.rail.model;

/**
 * Represents a location in which a train can reside.
 */
public class RailNetworkLocation {
    private int stanox;
    private int nationalLocationCode;
    private String shortCode;
    private String tiploc;
    private String description;
    private String platform;

    public RailNetworkLocation() {
    }

    public RailNetworkLocation(int stanox, int nationalLocationCode, String shortCode, String tiploc, String description) {
        this.stanox = stanox;
        this.nationalLocationCode = nationalLocationCode;
        setShortCode(shortCode);
        setTiploc(tiploc);
        setDescription(description);
    }

    public int getStanox() {
        return stanox;
    }

    public void setStanox(int stanox) {
        this.stanox = stanox;
    }

    public int getNationalLocationCode() {
        return nationalLocationCode;
    }

    public void setNationalLocationCode(int nationalLocationCode) {
        this.nationalLocationCode = nationalLocationCode;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode != null ? shortCode.intern() : null;
    }

    public String getTiploc() {
        return tiploc;
    }

    public void setTiploc(String tiploc) {
        this.tiploc = tiploc != null ? tiploc.intern() : null;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description != null ? description.intern() : null;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @Override
    public String toString() {
        String id = tiploc != null ? tiploc : null;
        id = id == null ? shortCode : id;
        id = id == null ? String.valueOf(stanox) : id;
        id = id == null ? String.valueOf(nationalLocationCode) : id;
        return String.format("RailNetworkLocation {%s}", id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RailNetworkLocation that = (RailNetworkLocation) o;

        if (stanox != that.stanox) return false;
        if (nationalLocationCode != that.nationalLocationCode) return false;
        if (shortCode != null ? !shortCode.equals(that.shortCode) : that.shortCode != null) return false;
        if (tiploc != null ? !tiploc.equals(that.tiploc) : that.tiploc != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = stanox;
        result = 31 * result + nationalLocationCode;
        result = 31 * result + (shortCode != null ? shortCode.hashCode() : 0);
        result = 31 * result + (tiploc != null ? tiploc.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
