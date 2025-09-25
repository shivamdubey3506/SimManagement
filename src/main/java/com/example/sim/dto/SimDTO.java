package com.example.sim.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SimDTO {
	
    @NotBlank(message= "{sim.number.notblank}")
    @Size(min = 10, max = 16, message="{sim.number.size}")
	private String simNumber;
	
	private String status;
	

	public String getSimNumber() {
		return simNumber;
	}

	public void setSimNumber(String simNumber) {
		this.simNumber = simNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
