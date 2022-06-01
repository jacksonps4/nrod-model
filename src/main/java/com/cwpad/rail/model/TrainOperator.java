package com.cwpad.rail.model;

public class TrainOperator {
	private String name;
	private String businessCode;
	private int sectorCode;
	private String atocCode;
	
	public TrainOperator() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBusinessCode() {
		return businessCode;
	}

	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}

	public int getSectorCode() {
		return sectorCode;
	}

	public void setSectorCode(int sectorCode) {
		this.sectorCode = sectorCode;
	}

	public String getAtocCode() {
		return atocCode;
	}

	public void setAtocCode(String atocCode) {
		this.atocCode = atocCode;
	}
}
