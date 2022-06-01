package com.cwpad.rail.model;


import java.time.LocalTime;

public class TrainPass {
    private String trainId;
    private int stanox;
    private String tiploc;
    private short arrivalHour = -1;
    private short arrivalMin = -1;
    private short passHour = -1;
    private short passMin = -1;
    private short departureHour = -1;
    private short departureMin = -1;
    private boolean callingPoint;
    private String areaId;
    private RailNetworkLocation location;
    private String platform;

    public TrainPass() {
    }

    public TrainPass(String trainId) {
        setTrainId(trainId);
    }

    public TrainPass(String trainId, int stanox, String tiploc, LocalTime departureTime, LocalTime passTime, LocalTime arrivalTime, boolean callingPoint, String areaId) {
        setTrainId(trainId);
        this.stanox = stanox;
        setTiploc(tiploc);
        setDepartureTime(departureTime);
        setPassTime(passTime);
        setArrivalTime(arrivalTime);
        this.callingPoint = callingPoint;
        setAreaId(areaId);
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId != null ? trainId.intern() : null;
    }

    public int getStanox() {
        return stanox;
    }

    public void setStanox(int stanox) {
        this.stanox = stanox;
    }

    public String getTiploc() {
        return tiploc;
    }

    public void setTiploc(String tiploc) {
        this.tiploc = tiploc != null ? tiploc.intern() : null;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public LocalTime getArrivalTime() {
        return convertHourAndMinuteToTime(arrivalHour, arrivalMin);
    }

    public LocalTime getPassTime() {
        return convertHourAndMinuteToTime(passHour, passMin);
    }

    public LocalTime getDepartureTime() {
        return convertHourAndMinuteToTime(departureHour, departureMin);
    }

    private LocalTime convertHourAndMinuteToTime(short hour, short min) {
        if (hour == -1 && min == -1) {
            return null;
        }
        int halfBit = this.passMin & 0x01;
        return LocalTime.of(hour, min >> 1, halfBit == 1 ? 30 : 0);
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalHour = (byte) arrivalTime.getHour();
        this.arrivalMin = ((byte) (arrivalTime.getMinute() << 1));
        int second = arrivalTime.getSecond();
        switch (second) {
            case 30:
                this.arrivalMin = (short) (this.arrivalMin | 0x01);
                break;
        }
    }

    public void setPassTime(LocalTime time) {
        this.passHour = (byte) time.getHour();
        this.passMin = ((byte) (time.getMinute() << 1));
        int second = time.getSecond();
        switch (second) {
            case 30:
                this.passMin = (short) (this.passMin | 0x01);
                break;
        }
    }

    public void setDepartureTime(LocalTime time) {
        this.departureHour = (byte) time.getHour();
        this.departureMin = ((byte) (time.getMinute() << 1));
        int second = time.getSecond();
        switch (second) {
            case 30:
                this.departureMin = (short) (this.departureMin | 0x01);
                break;
        }
    }

    public boolean isCallingPoint() {
        return callingPoint;
    }

    public void setCallingPoint(boolean callingPoint) {
        this.callingPoint = callingPoint;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId != null ? areaId.intern() : null;
    }

    public RailNetworkLocation getLocation() {
        return location;
    }

    public void setLocation(RailNetworkLocation location) {
        this.location = location;
    }
}
