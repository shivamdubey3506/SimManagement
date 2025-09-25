package com.example.sim.service;

import java.util.List;

import com.example.sim.dto.ActivateDTO;
import com.example.sim.dto.CustomerDTO;

import jakarta.validation.Valid;

public interface CustomerService {

	 String activateSIM(@Valid ActivateDTO activateDTO);

	 List<CustomerDTO> getCustomersByAadhar(String aadhar);

	 String deActivateSIM(@Valid ActivateDTO activateDTO);

}
