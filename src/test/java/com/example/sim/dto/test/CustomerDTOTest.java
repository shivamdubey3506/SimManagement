package com.example.sim.dto.test;

import jakarta.validation.*;
import org.junit.jupiter.api.*;

import com.example.sim.dto.CustomerDTO;
import com.example.sim.dto.SimDTO;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDTOTest {

    private Validator validator;

    @BeforeEach
    void setupValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testGetterAndSetter() {
        CustomerDTO dto = new CustomerDTO();
        SimDTO sim = new SimDTO();
        sim.setSimNumber("SIM1234567890");
        sim.setStatus("activated");

        dto.setName("John Doe");
        dto.setDob("1990-01-01");
        dto.setAadhar("123456789012");
        dto.setAddress("123 Main Street");
        dto.setPhoneNumber("9876543210");
        dto.setFatherName("Robert Doe");
        dto.setSimDTO(sim);

        assertEquals("John Doe", dto.getName());
        assertEquals("1990-01-01", dto.getDob());
        assertEquals("123456789012", dto.getAadhar());
        assertEquals("123 Main Street", dto.getAddress());
        assertEquals("9876543210", dto.getPhoneNumber());
        assertEquals("Robert Doe", dto.getFatherName());
        assertEquals(sim, dto.getSimDTO());
    }

    @Test
    void testValidCustomerDTO() {
        CustomerDTO dto = new CustomerDTO();
        SimDTO sim = new SimDTO();
        sim.setSimNumber("SIM1234567890");
        sim.setStatus("activated");

        dto.setName("Alice");
        dto.setDob("1995-05-15");
        dto.setAadhar("123456789012");
        dto.setAddress("456 Elm Street");
        dto.setPhoneNumber("9876543210");
        dto.setFatherName("George");
        dto.setSimDTO(sim);

        Set<ConstraintViolation<CustomerDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Expected no validation errors");
    }

    @Test
    void testBlankName() {
        CustomerDTO dto = createValidCustomer();
        dto.setName("");

        Set<ConstraintViolation<CustomerDTO>> violations = validator.validate(dto);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("name")));
    }

    @Test
    void testBlankDob() {
        CustomerDTO dto = createValidCustomer();
        dto.setDob("");

        Set<ConstraintViolation<CustomerDTO>> violations = validator.validate(dto);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("dob")));
    }

    @Test
    void testInvalidAadharSize() {
        CustomerDTO dto = createValidCustomer();
        dto.setAadhar("12345");

        Set<ConstraintViolation<CustomerDTO>> violations = validator.validate(dto);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("aadhar")));
    }

    @Test
    void testBlankAddress() {
        CustomerDTO dto = createValidCustomer();
        dto.setAddress("");

        Set<ConstraintViolation<CustomerDTO>> violations = validator.validate(dto);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("address")));
    }

    @Test
    void testInvalidPhoneNumberPattern() {
        CustomerDTO dto = createValidCustomer();
        dto.setPhoneNumber("1234567890");

        Set<ConstraintViolation<CustomerDTO>> violations = validator.validate(dto);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("phoneNumber")));
    }

    @Test
    void testBlankFatherName() {
        CustomerDTO dto = createValidCustomer();
        dto.setFatherName("");

        Set<ConstraintViolation<CustomerDTO>> violations = validator.validate(dto);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("fatherName")));
    }

    @Test
    void testInvalidSimDTO() {
        CustomerDTO dto = createValidCustomer();
        SimDTO sim = new SimDTO(); // missing required fields
        dto.setSimDTO(sim);

        Set<ConstraintViolation<CustomerDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty(), "Expected validation errors from SimDTO");
    }

    private CustomerDTO createValidCustomer() {
        CustomerDTO dto = new CustomerDTO();
        SimDTO sim = new SimDTO();
        sim.setSimNumber("SIM1234567890");
        sim.setStatus("activated");

        dto.setName("Alice");
        dto.setDob("1995-05-15");
        dto.setAadhar("123456789012");
        dto.setAddress("456 Elm Street");
        dto.setPhoneNumber("9876543210");
        dto.setFatherName("George");
        dto.setSimDTO(sim);

        return dto;
    }
}