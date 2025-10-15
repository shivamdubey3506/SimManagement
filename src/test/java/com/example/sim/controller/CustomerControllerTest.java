package com.example.sim.controller;



import com.example.sim.controller.CustomerController;
import com.example.sim.dto.AadharDTO;
import com.example.sim.dto.ActivateDTO;
import com.example.sim.dto.CustomerDTO;
import com.example.sim.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    public CustomerControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testActivateSIM_shouldReturnSuccessMessage() {
        ActivateDTO activateDTO = new ActivateDTO();
        String expectedMessage = "SIM Activated Successfully";

        when(customerService.activateSIM(activateDTO)).thenReturn(expectedMessage);

        ResponseEntity<String> response = customerController.activateSIM(activateDTO);

        assertEquals(expectedMessage, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(customerService).activateSIM(activateDTO);
    }

    @Test
    void testGetCustomerByAadhar_shouldReturnCustomerList() {
        AadharDTO aadharDTO = new AadharDTO();
        aadharDTO.setAadhar("123456789012");

        CustomerDTO customer = new CustomerDTO();
        List<CustomerDTO> customerList = List.of(customer);

        when(customerService.getCustomersByAadhar("123456789012")).thenReturn(customerList);

        ResponseEntity<?> response = customerController.getCustomerByAadhar(aadharDTO);

        assertEquals(customerList, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(customerService).getCustomersByAadhar("123456789012");
    }

    @Test
    void testGetCustomerByAadhar_shouldReturnNoRecordFound() {
        AadharDTO aadharDTO = new AadharDTO();
        aadharDTO.setAadhar("000000000000");

        when(customerService.getCustomersByAadhar("000000000000")).thenReturn(Collections.emptyList());

        ResponseEntity<?> response = customerController.getCustomerByAadhar(aadharDTO);

        assertEquals("No Record Found", response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(customerService).getCustomersByAadhar("000000000000");
    }

    @Test
    void testDeActivateSIM_shouldReturnSuccessMessage() {
        ActivateDTO activateDTO = new ActivateDTO();
        String expectedMessage = "SIM Deactivated Successfully";

        when(customerService.deActivateSIM(activateDTO)).thenReturn(expectedMessage);

        ResponseEntity<String> response = customerController.deActivateSIM(activateDTO);

        assertEquals(expectedMessage, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(customerService).deActivateSIM(activateDTO);
    }
}
