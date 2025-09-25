package com.example.sim.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.sim.dto.CustomerDTO;

import com.example.sim.service.SimService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/sim")
public class SimController {

	@Autowired
	private SimService simService;
	
	
	@PostMapping("/register")
	public ResponseEntity<String> registerSIM (@RequestBody @Valid CustomerDTO customerDTO){
		String message= simService.registerSIM(customerDTO);
		return ResponseEntity.ok(message);
	}
	
	@GetMapping("/")
	public ResponseEntity<?> getAllCustomers() {
	List<CustomerDTO> list = simService.getAllCustomers();
	if (list.isEmpty()) {
	return ResponseEntity.ok().body("No Record Found!.");

	}
      return ResponseEntity.ok(list);

	}
}
