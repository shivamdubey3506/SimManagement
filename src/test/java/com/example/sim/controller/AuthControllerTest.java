package com.example.sim.controller;

import com.example.sim.controller.AuthController;
import com.example.sim.dto.AuthRequestDTO;
import com.example.sim.dto.AuthResponseDTO;
import com.example.sim.service.CustomerService;
import com.example.sim.utility.JwtUtil;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    JwtUtil jwtUtil;
    @Mock
    CustomerService customerService;
    @InjectMocks
    AuthController authController;

    @Test
    void authenticate_validCredentials_returnsToken() {
        AuthRequestDTO dto = new AuthRequestDTO();
        dto.setAadhar("123456789012");
        dto.setDob("2000-01-01");
        when(customerService.validateAadhaarAndDob(anyString(), anyString())).thenReturn(true);
        when(jwtUtil.generateToken(anyString())).thenReturn("token");
        ResponseEntity<?> response = authController.authenticate(dto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof AuthResponseDTO);
        assertEquals("token", ((AuthResponseDTO)response.getBody()).getToken());
    }

    @Test
    void authenticate_invalidCredentials_returnsUnauthorized() {
        AuthRequestDTO dto = new AuthRequestDTO();
        dto.setAadhar("123456789012");
        dto.setDob("2000-01-01");
        when(customerService.validateAadhaarAndDob(anyString(), anyString())).thenReturn(false);
        ResponseEntity<?> response = authController.authenticate(dto);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Invalid Credintial", response.getBody());
    }
}
