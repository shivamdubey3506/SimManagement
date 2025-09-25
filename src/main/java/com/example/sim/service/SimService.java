package com.example.sim.service;

import java.util.List;

import com.example.sim.dto.CustomerDTO;

import jakarta.validation.Valid;

public interface SimService {

	String registerSIM(@Valid CustomerDTO customerDTO);

	List<CustomerDTO> getAllCustomers();

}
