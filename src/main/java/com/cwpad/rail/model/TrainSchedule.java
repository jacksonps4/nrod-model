package com.cwpad.rail.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class TrainSchedule {
    public enum ScheduleTransactionType {
        CANCELLATION, OVERLAY, SHORT_TERM, PERMANENT
    }

    private String scheduleUid;
    private String trainIdentity;
    private String headCode;
    private RailNetworkLocation originLocation;
    private String originShortCode;
    private String originTiploc;
    private String originAreaId;
    private String originPlatform;
    private LocalDate serviceRunDate;
    private byte originDepartureHour;
    private byte originDepartureMin;
    private byte destinationArrivalHour;
    private byte destinationArrivalMin;
    private RailNetworkLocation destinationLocation;
    private String destinationShortCode;
    private String destinationTiploc;
    private String destinationAreaId;
    private String destinationPlatform;
    private List<TrainPass> passes = new ArrayList<>();
    private ScheduleTransactionType scheduleTransactionType;
    private LocalDate validFrom;
    private LocalDate validTo;
    private TrainOperator trainOperator;
    private String serviceCode;
    private int maxSpeed = -1;
    private String powerType;
    private List<String> operatingCharacteristics;
    private List<String> catering;
    private String seatingClass;

    public TrainSchedule() {
    }

    public TrainSchedule(String scheduleUid) {
        this.scheduleUid = scheduleUid.intern();
    }

    public String getScheduleUid() {
        return scheduleUid;
    }

    public void setScheduleUid(String scheduleUid) {
        this.scheduleUid = scheduleUid;
    }

    public String getTrainIdentity() {
        return trainIdentity;
    }

    public void setTrainIdentity(String trainIdentity) {
        this.trainIdentity = trainIdentity;
    }

    public String getOriginShortCode() {
        return originShortCode;
    }

    public void setOriginShortCode(String originShortCode) {
        this.originShortCode = originShortCode;
    }

    public LocalDate getServiceRunDate() {
        return serviceRunDate;
    }

    public void setServiceRunDate(LocalDate serviceRunDate) {
        this.serviceRunDate = serviceRunDate;
    }

    public LocalTime getOriginDepartureTime() {
        return LocalTime.of(originDepartureHour, originDepartureMin);
    }

    public void setOriginDepartureTime(LocalTime originDepartureTime) {
        this.originDepartureHour = (byte) originDepartureTime.getHour();
        this.originDepartureMin = (byte) originDepartureTime.getMinute();
    }

    public LocalTime getDestinationArrivalTime() {
        return LocalTime.of(destinationArrivalHour, destinationArrivalMin);
    }

    public void setDestinationArrivalTime(LocalTime destinationArrivalTime) {
        this.destinationArrivalHour = (byte) destinationArrivalTime.getHour();
        this.destinationArrivalMin = (byte) destinationArrivalTime.getMinute();
    }

    public String getDestinationShortCode() {
        return destinationShortCode;
    }

    public void setDestinationShortCode(String destinationShortCode) {
        this.destinationShortCode = destinationShortCode;
    }

    public List<TrainPass> getPasses() {
        return passes;
    }

    public void setPasses(List<TrainPass> passes) {
        this.passes = passes;
    }

    public ScheduleTransactionType getScheduleTransactionType() {
        return scheduleTransactionType;
    }

    public void setScheduleTransactionType(ScheduleTransactionType scheduleTransactionType) {
        this.scheduleTransactionType = scheduleTransactionType;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDate getValidTo() {
        return validTo;
    }

    public void setValidTo(LocalDate validTo) {
        this.validTo = validTo;
    }

    public String getOriginTiploc() {
        return originTiploc;
    }

    public void setOriginTiploc(String originTiploc) {
        this.originTiploc = originTiploc;
    }

    public String getDestinationTiploc() {
        return destinationTiploc;
    }

    public void setDestinationTiploc(String destinationTiploc) {
        this.destinationTiploc = destinationTiploc;
    }

    public TrainOperator getTrainOperator() {
        return trainOperator;
    }

    public void setTrainOperator(TrainOperator trainOperator) {
        this.trainOperator = trainOperator;
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

    public Integer getMaxSpeed() {
        return maxSpeed != -1 ? maxSpeed : null;
    }

    public void setMaxSpeed(Integer maxSpeed) {
         this.maxSpeed = maxSpeed == null ? -1 : maxSpeed;
    }

    public String getPowerType() {
        return powerType;
    }

    public void setPowerType(String powerType) {
        this.powerType = powerType;
    }

    public List<String> getOperatingCharacteristics() {
        return operatingCharacteristics;
    }

    public void setOperatingCharacteristics(List<String> operatingCharacteristics) {
        this.operatingCharacteristics = operatingCharacteristics;
    }

    public List<String> getCatering() {
        return catering;
    }

    public void setCatering(List<String> catering) {
        this.catering = catering;
    }

    public String getSeatingClass() {
        return seatingClass;
    }

    public void setSeatingClass(String seatingClass) {
        this.seatingClass = seatingClass;
    }

    public Set<String> getCallingPointTiplocs() {
        Set<String> callingPoints = new LinkedHashSet<>();
        callingPoints.add(getOriginTiploc());
        callingPoints.addAll(getPasses().stream()
                .map(p -> p.getTiploc())
                .collect(Collectors.toList()));
        callingPoints.add(getDestinationTiploc());
        return callingPoints;
    }

    public void setCallingPointTiplocs(Set<String> callingPointTiplocs) {
        // no-op
    }

    public boolean isSupercededBy(TrainSchedule trainSchedule) {
        if (scheduleUid.equals(trainSchedule.getScheduleUid())) {
            if (trainSchedule.getValidFrom().compareTo(validFrom) >= 0 &&
                    trainSchedule.getValidTo().compareTo(validTo) <= 0) {
                return trainSchedule.getScheduleTransactionType().compareTo(scheduleTransactionType) < 0;
            }
        }

        return false;
    }

    public String getOriginAreaId() {
        return originAreaId;
    }

    public void setOriginAreaId(String originAreaId) {
        this.originAreaId = originAreaId;
    }

    public String getDestinationAreaId() {
        return destinationAreaId;
    }

    public void setDestinationAreaId(String destinationAreaId) {
        this.destinationAreaId = destinationAreaId;
    }

    public List<String> getAreaIdsPassed() {
        Set<String> areas = new LinkedHashSet<>();
        areas.add(originAreaId);
        passes.stream()
                .map(TrainPass::getAreaId)
                .forEach(areas::add);
        areas.add(destinationAreaId);
        return new LinkedList<>(areas);
    }

    public void setAreaIdsPassed(List<String> areaIdsPassed) {
        // no-op
    }

    public String getOriginPlatform() {
        return originPlatform;
    }

    public void setOriginPlatform(String originPlatform) {
        this.originPlatform = originPlatform;
    }

    public String getDestinationPlatform() {
        return destinationPlatform;
    }

    public void setDestinationPlatform(String destinationPlatform) {
        this.destinationPlatform = destinationPlatform;
    }

    public RailNetworkLocation getOriginLocation() {
        return originLocation;
    }

    public void setOriginLocation(RailNetworkLocation originLocation) {
        this.originLocation = originLocation;
    }

    public RailNetworkLocation getDestinationLocation() {
        return destinationLocation;
    }

    public void setDestinationLocation(RailNetworkLocation destinationLocation) {
        this.destinationLocation = destinationLocation;
    }
}
