package com.cwpad.rail.nrod.cif;

import java.time.Duration;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

abstract class CIFLocationRecord extends CIFRecord {
    private String tiploc;
    private LocalTime scheduledDeparture;
    private LocalTime publicDeparture;
    private String platform;
    private String line;
    private String path;
    private List<Activity> activities;
    private Duration engineeringAllowance;
    private Duration pathingAllowance;
    private Duration performanceAllowance;

    public CIFLocationRecord(CIFRecordType cifRecordType) {
        super(cifRecordType);
    }

    public String getTiploc() {
        return tiploc;
    }

    public void setTiploc(String tiploc) {
        this.tiploc = tiploc;
    }

    public LocalTime getScheduledDeparture() {
        return scheduledDeparture;
    }

    public void setScheduledDeparture(LocalTime scheduledDeparture) {
        this.scheduledDeparture = scheduledDeparture;
    }

    public LocalTime getPublicDeparture() {
        return publicDeparture;
    }

    public void setPublicDeparture(LocalTime publicDeparture) {
        this.publicDeparture = publicDeparture;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Duration getEngineeringAllowance() {
        return engineeringAllowance;
    }

    public void setEngineeringAllowance(Duration engineeringAllowance) {
        this.engineeringAllowance = engineeringAllowance;
    }

    public Duration getPathingAllowance() {
        return pathingAllowance;
    }

    public void setPathingAllowance(Duration pathingAllowance) {
        this.pathingAllowance = pathingAllowance;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public void addActivity(Activity activity) {
        if (this.activities == null) {
            this.activities = new LinkedList<>();
        }
        this.activities.add(activity);
    }

    public Duration getPerformanceAllowance() {
        return performanceAllowance;
    }

    public void setPerformanceAllowance(Duration performanceAllowance) {
        this.performanceAllowance = performanceAllowance;
    }
}
