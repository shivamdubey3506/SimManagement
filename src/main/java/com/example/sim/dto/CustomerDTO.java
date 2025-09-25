package com.example.sim.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CustomerDTO {
	
	@NotBlank(message= "{customer.name.notblank}")
	private String name;
	
	@NotBlank(message= "{customer.dob.notblank}")
	private String dob;
	
	@NotBlank(message = "{customer.aadhar.notblank}")
	@Size(min = 12, max = 12, message ="{customer.aadhar.size}")
	private String aadhar;
	
	@NotBlank(message= "{customer.address.notblank}")
	private String address;
	
	@NotBlank(message= "{customer.phone.notblank}")
	@Pattern(regexp= "^[6-9][0-9]{9}$", message="{customer.phone.invalid}")
	private String phoneNumber;
	
	@NotBlank(message= "{customer.father.notblank}")
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
