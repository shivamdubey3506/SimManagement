package com.example.sim.controller;

import com.example.sim.controller.SimController;
import com.example.sim.dto.CustomerDTO;
import com.example.sim.service.SimService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SimControllerTest {
    @Mock
    private SimService simService;
    @InjectMocks
    private SimController simController;
    @BeforeEach
    void setUp() { MockitoAnnotations.openMocks(this); }

    @Test
    void testRegisterSIM_shouldReturnSuccessMessage() {
        CustomerDTO customerDTO = new CustomerDTO();
        String expectedMessage = "SIM Registered Successfully";
        when(simService.registerSIM(customerDTO)).thenReturn(expectedMessage);
        ResponseEntity<String> response = simController.registerSIM(customerDTO);
        assertEquals(expectedMessage, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(simService).registerSIM(customerDTO);
    }

    @Test
    void testGetAllCustomers_shouldReturnList() {
        CustomerDTO customer = new CustomerDTO();
        List<CustomerDTO> mockList = List.of(customer);
        when(simService.getAllCustomers()).thenReturn(mockList);
        ResponseEntity<?> response = simController.getAllCustomers();
        assertEquals(mockList, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(simService).getAllCustomers();
    }

    @Test
    void testGetAllCustomers_shouldReturnNoRecordFound() {
        when(simService.getAllCustomers()).thenReturn(Collections.emptyList());
        ResponseEntity<?> response = simController.getAllCustomers();
        assertEquals("No Record Found!.", response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(simService).getAllCustomers();
    }
}
