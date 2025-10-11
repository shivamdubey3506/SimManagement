package com.example.sim.service.test;


import com.example.sim.dto.ActivateDTO;
import com.example.sim.dto.CustomerDTO;
import com.example.sim.model.Customer;
import com.example.sim.model.RecordHistory;
import com.example.sim.model.Sim;
import com.example.sim.repository.CustomerRepository;
import com.example.sim.repository.RecordHistoryRepository;
import com.example.sim.repository.SimRepository;
import com.example.sim.service.CustomerServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceImplTest {

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private SimRepository simRepository;

    @Mock
    private RecordHistoryRepository recordHistoryRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testActivateSIM_whenAlreadyActivated() {
        ActivateDTO dto = new ActivateDTO();
        dto.setAadhar("123456789012");
        dto.setPhoneNumber("9876543210");
        dto.setDob("1990-01-01");

        Sim sim = new Sim();
        sim.setId(1L);
        sim.setStatus("activated");

        Customer customer = new Customer();
        customer.setSim(sim);

        when(customerRepository.findByAadharAndPhoneNumberAndDob(dto.getAadhar(), dto.getPhoneNumber(), dto.getDob()))
                .thenReturn(Optional.of(customer));

        when(simRepository.findById(1L)).thenReturn(Optional.of(sim));

        String result = customerService.activateSIM(dto);
        assertEquals("your sim is already activated", result);
    }

    @Test
    void testActivateSIM_whenActivationSuccessful() {
        ActivateDTO dto = new ActivateDTO();
        dto.setAadhar("123456789012");
        dto.setPhoneNumber("9876543210");
        dto.setDob("1990-01-01");

        Sim sim = new Sim();
        sim.setId(1L);
        sim.setStatus("pending");

        Customer customer = new Customer();
        customer.setSim(sim);

        Customer customerByPhone = new Customer();
        customerByPhone.setName("John Doe");

        when(customerRepository.findByAadharAndPhoneNumberAndDob(dto.getAadhar(), dto.getPhoneNumber(), dto.getDob()))
                .thenReturn(Optional.of(customer));
        when(simRepository.findById(1L)).thenReturn(Optional.of(sim));
        when(customerRepository.findByPhoneNumber(dto.getPhoneNumber())).thenReturn(Optional.of(customerByPhone));

        String result = customerService.activateSIM(dto);
        assertEquals("Your SIM is activated", result);
        verify(simRepository).save(sim);
        verify(recordHistoryRepository).save(any(RecordHistory.class));
    }

    @Test
    void testActivateSIM_whenDetailsDoNotMatch() {
        ActivateDTO dto = new ActivateDTO();
        dto.setAadhar("invalid");
        dto.setPhoneNumber("0000000000");
        dto.setDob("2000-01-01");

        when(customerRepository.findByAadharAndPhoneNumberAndDob(dto.getAadhar(), dto.getPhoneNumber(), dto.getDob()))
                .thenReturn(Optional.empty());

        String result = customerService.activateSIM(dto);
        assertEquals("Activation failed . Details do not match", result);
    }

    @Test
    void testDeActivateSIM_whenAlreadyDeactivated() {
        ActivateDTO dto = new ActivateDTO();
        dto.setAadhar("123456789012");
        dto.setPhoneNumber("9876543210");
        dto.setDob("1990-01-01");

        Sim sim = new Sim();
        sim.setId(1L);
        sim.setStatus("deactivated");

        Customer customer = new Customer();
        customer.setSim(sim);

        when(customerRepository.findByAadharAndPhoneNumberAndDob(dto.getAadhar(), dto.getPhoneNumber(), dto.getDob()))
                .thenReturn(Optional.of(customer));
        when(simRepository.findById(1L)).thenReturn(Optional.of(sim));

        String result = customerService.deActivateSIM(dto);
        assertEquals("Your SIM is Already De-Activated", result);
    }

    @Test
    void testDeActivateSIM_whenDeactivationSuccessful() {
        ActivateDTO dto = new ActivateDTO();
        dto.setAadhar("123456789012");
        dto.setPhoneNumber("9876543210");
        dto.setDob("1990-01-01");

        Sim sim = new Sim();
        sim.setId(1L);
        sim.setStatus("activated");

        Customer customer = new Customer();
        customer.setSim(sim);

        Customer customerByPhone = new Customer();
        customerByPhone.setName("Admin");

        when(customerRepository.findByAadharAndPhoneNumberAndDob(dto.getAadhar(), dto.getPhoneNumber(), dto.getDob()))
                .thenReturn(Optional.of(customer));
        when(simRepository.findById(1L)).thenReturn(Optional.of(sim));
        when(customerRepository.findByPhoneNumber(dto.getPhoneNumber())).thenReturn(Optional.of(customerByPhone));

        String result = customerService.deActivateSIM(dto);
        assertEquals(" Your SIM is DE-Activated", result);
        verify(simRepository).save(sim);
        verify(recordHistoryRepository).save(any(RecordHistory.class));
    }

    @Test
    void testDeActivateSIM_whenDetailsDoNotMatch() {
        ActivateDTO dto = new ActivateDTO();
        dto.setAadhar("invalid");
        dto.setPhoneNumber("0000000000");
        dto.setDob("2000-01-01");

        when(customerRepository.findByAadharAndPhoneNumberAndDob(dto.getAadhar(), dto.getPhoneNumber(), dto.getDob()))
                .thenReturn(Optional.empty());

        String result = customerService.deActivateSIM(dto);
        assertEquals("Activation failed.Details do not match", result);
    }

    @Test
    void testGetCustomersByAadhar_returnsMappedDTOs() {
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

        when(customerRepository.findByAadhar("123456789012")).thenReturn(List.of(customer));

        List<CustomerDTO> result = customerService.getCustomersByAadhar("123456789012");

        assertEquals(1, result.size());
        assertEquals("Alice", result.get(0).getName());
        assertEquals("SIM123456", result.get(0).getSimDTO().getSimNumber());
    }

    @Test
    void testValidateAadhaarAndDob_whenValid() {
        Customer customer = new Customer();
        when(customerRepository.findByAadharAndDob("123456789012", "1990-01-01"))
                .thenReturn(Optional.of(customer));

        boolean result = customerService.validateAadhaarAndDob("123456789012", "1990-01-01");
        assertTrue(result);
    }

    @Test
    void testValidateAadhaarAndDob_whenInvalid() {
        when(customerRepository.findByAadharAndDob("invalid", "2000-01-01"))
                .thenReturn(Optional.empty());

        boolean result = customerService.validateAadhaarAndDob("invalid", "2000-01-01");
        assertFalse(result);
    }
}
