package com.example.sim.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.sim.dto.ActivateDTO;
import com.example.sim.dto.CustomerDTO;
import com.example.sim.dto.SimDTO;
import com.example.sim.model.Customer;
import com.example.sim.model.RecordHistory;
import com.example.sim.model.Sim;
import com.example.sim.repository.CustomerRepository;
import com.example.sim.repository.RecordHistoryRepository;
import com.example.sim.repository.SimRepository;
import jakarta.validation.Valid;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private SimRepository simRepository;
	@Autowired
	private RecordHistoryRepository recordHistoryRepository;

	@Override
	public String activateSIM(@Valid ActivateDTO activateDTO) {
		Optional<Customer> customerOpt = customerRepository.findByAadharAndPhoneNumberAndDob
				(activateDTO.getAadhar(),activateDTO.getPhoneNumber(),activateDTO.getDob());
		
		if(customerOpt.isPresent()) {
			Optional<Sim> simOpt =simRepository.findById(customerOpt.get().getSim().getId());
			if(simOpt.isPresent()) {
				Sim sim =simOpt.get();
				if(sim.getStatus().equals("activated")) {
					return "your sim is already activated";
				}else {
					String oldStatus= sim.getStatus();
					sim.setStatus("activated");
					simRepository.save(sim);
					
					Optional<Customer> optional =customerRepository.findByPhoneNumber(activateDTO.getPhoneNumber());
					
					RecordHistory recordHistory= new RecordHistory();
					recordHistory.setOldStatus(oldStatus);
					recordHistory.setNewStatus("activated");
					recordHistory.setChangedAt(LocalDateTime.now());
					recordHistory.setChangedBy(optional.get().getName());
					recordHistory.setPhoneNumber(activateDTO.getPhoneNumber());
					recordHistoryRepository.save(recordHistory);
					
					return "Your SIM is activated";
				} 
			}
		}
		return "Activation failed . Details do not match";
	}

	
	
	@Override
	public List<CustomerDTO> getCustomersByAadhar(String aadhar) {
		
		List<Customer> customers=customerRepository.findByAadhar(aadhar);
		List<CustomerDTO> customerDTOs =new ArrayList<>();
		for (Customer customer : customers) {
			
			CustomerDTO dto =new CustomerDTO();
			dto.setName(customer.getName());
			dto.setDob(customer.getDob());
			dto.setAadhar(customer.getAadhar());
			dto.setAddress(customer.getAddress());
			dto.setPhoneNumber(customer.getPhoneNumber());
			dto.setFatherName(customer.getFatherName());
			
			Sim sim = customer.getSim();
			if(sim !=null) {
				SimDTO simDTO = new SimDTO();
				simDTO.setStatus(sim.getStatus());
				simDTO.setSimNumber(sim.getSimNumber());
				dto.setSimDTO(simDTO);
			}
			customerDTOs.add(dto);
		}
		
		return customerDTOs;
	}



	@Override
	public String deActivateSIM(@Valid ActivateDTO activateDTO) {
		Optional<Customer> customerOpt =customerRepository.findByAadharAndPhoneNumberAndDob
				(activateDTO.getAadhar(),activateDTO.getPhoneNumber(),activateDTO.getDob());
		if (customerOpt.isPresent()){
			Optional<Sim>  simOpt=simRepository.findById(customerOpt.get().getSim().getId());
			if(simOpt.isPresent()) {
				Sim sim=simOpt.get();
				if(sim.getStatus().equals("deactivated")){
						return "Your SIM is Already De-Activated";
			}else {
				String oldStatus = sim.getStatus();
				sim.setStatus("deactivated");
				simRepository.save(sim);
				
				Optional<Customer>optional=customerRepository.findByPhoneNumber(activateDTO.getPhoneNumber());
				
				RecordHistory recordHistord=new RecordHistory();
				recordHistord.setOldStatus(oldStatus);
				recordHistord.setNewStatus("deactivated");
				recordHistord.setChangedAt(LocalDateTime.now());
				recordHistord.setPhoneNumber(activateDTO.getPhoneNumber());
				recordHistord.setChangedBy(optional.get().getName());
				recordHistoryRepository.save(recordHistord);
				
				return" Your SIM is DE-Activated";
			}
		}
		}
		return "Activation failed.Details do not match";
	}

}
