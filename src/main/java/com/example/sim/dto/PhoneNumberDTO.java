package com.example.sim.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class PhoneNumberDTO {
	
	@NotBlank(message= "{customer.phone.notblank}")
	@Pattern(regexp= "^[6-9][0-9]{9}$", message="{customer.phone.invalid}")
	private String phoneNumber;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	

}
