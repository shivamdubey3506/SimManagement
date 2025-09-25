package com.example.sim.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sim.dto.CustomerDTO;
import com.example.sim.dto.SimDTO;
import com.example.sim.model.Customer;
import com.example.sim.model.Sim;
import com.example.sim.repository.CustomerRepository;
import com.example.sim.repository.SimRepository;

@Service
public class SimServiceImpl implements SimService{
	
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private SimRepository simRepository;

	@Override
	public String registerSIM( CustomerDTO customerDTO) {
		
		if((customerRepository.findByPhoneNumber(customerDTO.getPhoneNumber()).isPresent())
			|| simRepository.findBySimNumber(customerDTO.getSimDTO().getSimNumber()).isPresent()) {
				
				return "This phone number or sim number is already registered with another customer";
				
			}else {
				Customer customer =new Customer();
				customer.setName(customerDTO.getName());
				customer.setAadhar(customerDTO.getAadhar());
				customer.setDob(customerDTO.getDob());
				customer.setAddress(customerDTO.getAddress());
				customer.setPhoneNumber(customerDTO.getPhoneNumber());
				customer.setFatherName(customerDTO.getFatherName());
				Sim sim =new Sim();
				sim.setSimNumber(customerDTO.getSimDTO().getSimNumber());
				sim.setStatus("deactivated");
				customer.setSim(sim);
				sim.setCustomer(customer);
				customerRepository.save(customer);
				return "Sim Registration Succesfull";
				
			}
	}
	
	@Override
	public List<CustomerDTO> getAllCustomers() {

		List<Customer> customers  =customerRepository.findAll(); 
	List<CustomerDTO> customerDTOs= new ArrayList<>();

	for (Customer customer: customers) {

	CustomerDTO dto =new CustomerDTO();
	dto.setName(customer.getName()); 
	dto.setDob(customer.getDob());
	dto.setAadhar (customer.getAadhar());
	dto.setAddress(customer.getAddress());
	dto.setPhoneNumber( customer.getPhoneNumber());
	dto.setFatherName(customer.getFatherName());

	Sim sim= customer.getSim();

	if (sim != null) {
	SimDTO simDTO = new SimDTO();
	simDTO.setStatus(sim.getStatus()); 
	simDTO.setSimNumber(sim.getSimNumber());
	dto.setSimDTO(simDTO);
	}
	customerDTOs.add(dto);
	}
	return customerDTOs;

	}

	}



