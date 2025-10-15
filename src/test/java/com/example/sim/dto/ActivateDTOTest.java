package com.example.sim.dto;

import jakarta.validation.*;
import org.junit.jupiter.api.*;

import com.example.sim.dto.ActivateDTO;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ActivateDTOTest {

    private Validator validator;

    @BeforeEach
    void setupValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testGetterAndSetter() {
        ActivateDTO dto = new ActivateDTO();

        dto.setAadhar("123456789012");
        dto.setPhoneNumber("9876543210");
        dto.setDob("1990-01-01");

        assertEquals("123456789012", dto.getAadhar());
        assertEquals("9876543210", dto.getPhoneNumber());
        assertEquals("1990-01-01", dto.getDob());
    }

    @Test
    void testValidActivateDTO() {
        ActivateDTO dto = new ActivateDTO();
        dto.setAadhar("123456789012");
        dto.setPhoneNumber("9876543210");
        dto.setDob("1990-01-01");

        Set<ConstraintViolation<ActivateDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Expected no validation errors");
    }

    @Test
    void testBlankAadhar() {
        ActivateDTO dto = new ActivateDTO();
        dto.setAadhar("");
        dto.setPhoneNumber("9876543210");
        dto.setDob("1990-01-01");

        Set<ConstraintViolation<ActivateDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("aadhar")));
    }

    @Test
    void testInvalidAadharSize() {
        ActivateDTO dto = new ActivateDTO();
        dto.setAadhar("12345");
        dto.setPhoneNumber("9876543210");
        dto.setDob("1990-01-01");

        Set<ConstraintViolation<ActivateDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("aadhar")));
    }

    @Test
    void testBlankPhoneNumber() {
        ActivateDTO dto = new ActivateDTO();
        dto.setAadhar("123456789012");
        dto.setPhoneNumber("");
        dto.setDob("1990-01-01");

        Set<ConstraintViolation<ActivateDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("phoneNumber")));
    }

    @Test
    void testInvalidPhoneNumberPattern() {
        ActivateDTO dto = new ActivateDTO();
        dto.setAadhar("123456789012");
        dto.setPhoneNumber("1234567890"); // Starts with 1–5, invalid
        dto.setDob("1990-01-01");

        Set<ConstraintViolation<ActivateDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("phoneNumber")));
    }

    @Test
    void testBlankDob() {
        ActivateDTO dto = new ActivateDTO();
        dto.setAadhar("123456789012");
        dto.setPhoneNumber("9876543210");
        dto.setDob("");

        Set<ConstraintViolation<ActivateDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("dob")));
    }
}