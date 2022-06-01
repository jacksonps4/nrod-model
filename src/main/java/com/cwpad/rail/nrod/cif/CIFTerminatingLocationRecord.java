package com.cwpad.rail.nrod.cif;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

class CIFTerminatingLocationRecord extends CIFRecord {
    private String tiploc;
    private LocalTime scheduledArrival;
    private LocalTime publicArrival;
    private String platform;
    private String path;
    private List<Activity> activities;

    public CIFTerminatingLocationRecord() {
        super(CIFRecordType.LT);
    }

    public String getTiploc() {
        return tiploc;
    }

    public void setTiploc(String tiploc) {
        this.tiploc = tiploc;
    }

    public LocalTime getScheduledArrival() {
        return scheduledArrival;
    }

    public void setScheduledArrival(LocalTime scheduledArrival) {
        this.scheduledArrival = scheduledArrival;
    }

    public LocalTime getPublicArrival() {
        return publicArrival;
    }

    public void setPublicArrival(LocalTime publicArrival) {
        this.publicArrival = publicArrival;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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
}
