package com.example.sim.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.sim.dto.AadharDTO;
import com.example.sim.dto.ActivateDTO;
import com.example.sim.dto.CustomerDTO;
import com.example.sim.service.CustomerService;
import jakarta.validation.Valid;

//this is  project
@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/activate")
	public ResponseEntity<String> activateSIM (@RequestBody @Valid ActivateDTO activateDTO){
		String message = customerService.activateSIM(activateDTO);
		return ResponseEntity.ok(message);	
		
		
	}
	
	    @PostMapping("/byaadhar")
		public ResponseEntity<?> getCustomerByAadhar (@RequestBody  @Valid AadharDTO aadharDTO){
			List<CustomerDTO> list = customerService.getCustomersByAadhar(aadharDTO.getAadhar());
			if(list.isEmpty()) {
				return ResponseEntity.ok().body("No Record Found");
			}
		return ResponseEntity.ok(list);
	}
	
	    @PostMapping("/deactivate")
	    public ResponseEntity<String> deActivateSIM(@RequestBody @Valid ActivateDTO activateDTO){
	    	String message = customerService.deActivateSIM(activateDTO);
	    	return ResponseEntity.ok(message);
	    }
	
	
}
