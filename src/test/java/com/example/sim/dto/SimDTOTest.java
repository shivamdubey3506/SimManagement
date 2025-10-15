package com.example.sim.dto;

import jakarta.validation.*;
import org.junit.jupiter.api.*;

import com.example.sim.dto.SimDTO;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SimDTOTest {

    private Validator validator;

    @BeforeEach
    void setupValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testGetterAndSetter() {
        SimDTO dto = new SimDTO();
        dto.setSimNumber("SIM12345678");
        dto.setStatus("activated");

        assertEquals("SIM12345678", dto.getSimNumber());
        assertEquals("activated", dto.getStatus());
    }

    @Test
    void testValidSimNumber() {
        SimDTO dto = new SimDTO();
        dto.setSimNumber("SIM12345678");
        dto.setStatus("active");

        Set<ConstraintViolation<SimDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Expected no validation errors");
    }

    @Test
    void testBlankSimNumber() {
        SimDTO dto = new SimDTO();
        dto.setSimNumber(""); // Blank
        dto.setStatus("active");

        Set<ConstraintViolation<SimDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty(), "Expected validation error for blank simNumber");

        boolean hasNotBlankViolation = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("simNumber"));
        assertTrue(hasNotBlankViolation);
    }

    @Test
    void testShortSimNumber() {
        SimDTO dto = new SimDTO();
        dto.setSimNumber("SIM123"); // Too short
        dto.setStatus("active");

        Set<ConstraintViolation<SimDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty(), "Expected validation error for short simNumber");

        boolean hasSizeViolation = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("simNumber"));
        assertTrue(hasSizeViolation);
    }

    @Test
    void testLongSimNumber() {
        SimDTO dto = new SimDTO();
        dto.setSimNumber("SIM123456789012345"); // Too long
        dto.setStatus("active");

        Set<ConstraintViolation<SimDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty(), "Expected validation error for long simNumber");

        boolean hasSizeViolation = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("simNumber"));
        assertTrue(hasSizeViolation);
    }

    @Test
    void testNullSimNumber() {
        SimDTO dto = new SimDTO();
        dto.setSimNumber(null); // Null
        dto.setStatus("active");

        Set<ConstraintViolation<SimDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty(), "Expected validation error for null simNumber");

        boolean hasNotBlankViolation = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("simNumber"));
        assertTrue(hasNotBlankViolation);
    }

    @Test
    void testNullStatus() {
        SimDTO dto = new SimDTO();
        dto.setSimNumber("SIM12345678");
        dto.setStatus(null); // Optional field

        Set<ConstraintViolation<SimDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Expected no validation errors for null status");
    }
}