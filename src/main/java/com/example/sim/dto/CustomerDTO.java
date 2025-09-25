package com.example.sim.dto;

import jakarta.validation.Valid;

public class CustomerDTO {
	
	private String name;
	
	private String dob;
	
	private String aadhar;
	
	private String address;
	
	private String phoneNumber;
	
	private String fatherName;
	
	@Valid
	private SimDTO simDTO;
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getAadhar() {
		return aadhar;
	}

	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public SimDTO getSimDTO() {
		return simDTO;
	}

	public void setSimDTO(SimDTO simDTO) {
		this.simDTO = simDTO;
	}
	
	
	
	

}
