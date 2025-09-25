package com.example.sim.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AadharDTO {
	
	@NotBlank(message ="{customer.aadhar.notblank}")
	@Size(min=12, max=12, message="{customer .aadhar.size}")
	private String aadhar;
	
	

	public String getAadhar() {
		return aadhar;
	}

	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}
	
	

}
