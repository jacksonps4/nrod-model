package com.cwpad.rail.nrod.cif;

import java.util.LinkedList;
import java.util.List;

abstract class CIFTrainCharacteristics extends CIFRecord {
    private TrainCategory trainCategory;
    private String identity;
    private String headCode;
    private String serviceCode;
    private char portionId;
    private PowerType powerType;
    private TimingLoad timingLoad;
    private Integer maxSpeed;
    private List<OperatingCharacteristic> operatingCharacteristics;
    private SeatingClass seatingClass;
    private Sleepers sleepers;
    private Reservations reservations;
    private List<Catering> catering;
    private List<ServiceBranding> serviceBranding;

    public CIFTrainCharacteristics(CIFRecordType cifRecordType) {
        super(cifRecordType);
    }

    public TrainCategory getTrainCategory() {
        return trainCategory;
    }

    public void setTrainCategory(TrainCategory trainCategory) {
        this.trainCategory = trainCategory;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getHeadCode() {
        return headCode;
    }

    public void setHeadCode(String headCode) {
        this.headCode = headCode;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public char getPortionId() {
        return portionId;
    }

    public void setPortionId(char portionId) {
        this.portionId = portionId;
    }

    public PowerType getPowerType() {
        return powerType;
    }

    public void setPowerType(PowerType powerType) {
        this.powerType = powerType;
    }

    public TimingLoad getTimingLoad() {
        return timingLoad;
    }

    public void setTimingLoad(TimingLoad timingLoad) {
        this.timingLoad = timingLoad;
    }

    public Integer getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Integer maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public List<OperatingCharacteristic> getOperatingCharacteristics() {
        return operatingCharacteristics;
    }

    public void setOperatingCharacteristics(List<OperatingCharacteristic> operatingCharacteristics) {
        this.operatingCharacteristics = operatingCharacteristics;
    }

    public void addOperatingCharacteristic(OperatingCharacteristic operatingCharacteristic) {
        if (this.operatingCharacteristics == null) {
            this.operatingCharacteristics = new LinkedList<>();
        }
        this.operatingCharacteristics.add(operatingCharacteristic);
    }

    public SeatingClass getSeatingClass() {
        return seatingClass;
    }

    public void setSeatingClass(SeatingClass seatingClass) {
        this.seatingClass = seatingClass;
    }

    public Sleepers getSleepers() {
        return sleepers;
    }

    public void setSleepers(Sleepers sleepers) {
        this.sleepers = sleepers;
    }

    public Reservations getReservations() {
        return reservations;
    }

    public void setReservations(Reservations reservations) {
        this.reservations = reservations;
    }

    public List<Catering> getCatering() {
        return catering;
    }

    public void setCatering(List<Catering> catering) {
        this.catering = catering;
    }

    public void addCatering(Catering catering) {
        if (this.catering == null) {
            this.catering = new LinkedList<>();
        }
        this.catering.add(catering);
    }

    public List<ServiceBranding> getServiceBranding() {
        return serviceBranding;
    }

    public void setServiceBranding(List<ServiceBranding> serviceBranding) {
        this.serviceBranding = serviceBranding;
    }

    public void addServiceBranding(ServiceBranding serviceBranding) {
        if (this.serviceBranding == null) {
            this.serviceBranding = new LinkedList<>();
        }
        this.serviceBranding.add(serviceBranding);
    }
}
