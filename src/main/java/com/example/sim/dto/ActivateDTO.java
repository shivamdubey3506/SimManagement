package com.example.sim.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ActivateDTO {
	
	@NotBlank(message= "{customer.aadhar.notblank}")
	@Size(min=12, max=12, message="{customer.aadhar.size}")
	private String aadhar;
	
	@NotBlank(message ="{customer.phone.notblank}")
	@Pattern(regexp= "^[6-9]\\d{9}$", message ="{customer.phone.invalid}")
	private String phoneNumber;
	
	@NotBlank(message="{customer.dob.notblank}")
	private String dob;
	

	public String getAadhar() {
		return aadhar;
	}

	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}
	
	

}
