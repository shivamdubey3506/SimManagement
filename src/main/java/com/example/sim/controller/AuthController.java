
package com.example.sim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.sim.dto.AuthRequestDTO;
import com.example.sim.dto.AuthResponseDTO;
import com.example.sim.service.CustomerService;
import com.example.sim.utility.JwtUtil;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomerService customerService;
    

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody @Valid AuthRequestDTO authRequestDTO){
    	
    	if(customerService.validateAadhaarAndDob(authRequestDTO.getAadhar(),authRequestDTO.getDob())) {
    		
    		String token =jwtUtil.generateToken(authRequestDTO.getAadhar());
    		return ResponseEntity.ok(new AuthResponseDTO(token));
    	}else {
    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credintial");
    	}
    }
}
