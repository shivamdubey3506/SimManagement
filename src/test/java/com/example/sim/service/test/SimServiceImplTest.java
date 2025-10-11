package com.example.sim.service.test;


import com.example.sim.dto.CustomerDTO;
import com.example.sim.dto.SimDTO;
import com.example.sim.model.Customer;
import com.example.sim.model.Sim;
import com.example.sim.repository.CustomerRepository;
import com.example.sim.repository.SimRepository;
import com.example.sim.service.SimServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SimServiceImplTest {

    @InjectMocks
    private SimServiceImpl simService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private SimRepository simRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterSIM_whenPhoneOrSimAlreadyExists() {
        CustomerDTO dto = createCustomerDTO("9876543210", "SIM123456");

        when(customerRepository.findByPhoneNumber("9876543210")).thenReturn(Optional.of(new Customer()));
        when(simRepository.findBySimNumber("SIM123456")).thenReturn(Optional.empty());

        String result = simService.registerSIM(dto);
        assertEquals("This phone number or sim number is already registered with another customer", result);
    }

    @Test
    void testRegisterSIM_whenSimAlreadyExists() {
        CustomerDTO dto = createCustomerDTO("9876543210", "SIM123456");

        when(customerRepository.findByPhoneNumber("9876543210")).thenReturn(Optional.empty());
        when(simRepository.findBySimNumber("SIM123456")).thenReturn(Optional.of(new Sim()));

        String result = simService.registerSIM(dto);
        assertEquals("This phone number or sim number is already registered with another customer", result);
    }

    @Test
    void testRegisterSIM_successfulRegistration() {
        CustomerDTO dto = createCustomerDTO("9876543210", "SIM123456");

        when(customerRepository.findByPhoneNumber("9876543210")).thenReturn(Optional.empty());
        when(simRepository.findBySimNumber("SIM123456")).thenReturn(Optional.empty());

        String result = simService.registerSIM(dto);
        assertEquals("Sim Registration Succesfull", result);
        verify(customerRepository).save(any(Customer.class));
    }

    @Test
    void testGetAllCustomers_withSimData() {
        Sim sim = new Sim();
        sim.setSimNumber("SIM123456");
        sim.setStatus("activated");

        Customer customer = new Customer();
        customer.setName("Alice");
        customer.setDob("1995-05-15");
        customer.setAadhar("123456789012");
        customer.setAddress("456 Elm Street");
        customer.setPhoneNumber("9876543210");
        customer.setFatherName("George");
        customer.setSim(sim);

        when(customerRepository.findAll()).thenReturn(List.of(customer));

        List<CustomerDTO> result = simService.getAllCustomers();

        assertEquals(1, result.size());
        CustomerDTO dto = result.get(0);
        assertEquals("Alice", dto.getName());
        assertEquals("SIM123456", dto.getSimDTO().getSimNumber());
        assertEquals("activated", dto.getSimDTO().getStatus());
    }

    @Test
    void testGetAllCustomers_withoutSimData() {
        Customer customer = new Customer();
        customer.setName("Bob");
        customer.setDob("1990-01-01");
        customer.setAadhar("987654321098");
        customer.setAddress("789 Oak Street");
        customer.setPhoneNumber("9123456789");
        customer.setFatherName("Henry");
        customer.setSim(null);

        when(customerRepository.findAll()).thenReturn(List.of(customer));

        List<CustomerDTO> result = simService.getAllCustomers();

        assertEquals(1, result.size());
        CustomerDTO dto = result.get(0);
        assertEquals("Bob", dto.getName());
        assertNull(dto.getSimDTO());
    }

    private CustomerDTO createCustomerDTO(String phone, String simNumber) {
        CustomerDTO dto = new CustomerDTO();
        dto.setName("John Doe");
        dto.setDob("1990-01-01");
        dto.setAadhar("123456789012");
        dto.setAddress("123 Main Street");
        dto.setPhoneNumber(phone);
        dto.setFatherName("Robert Doe");

        SimDTO simDTO = new SimDTO();
        simDTO.setSimNumber(simNumber);
        dto.setSimDTO(simDTO);

        return dto;
    }
}