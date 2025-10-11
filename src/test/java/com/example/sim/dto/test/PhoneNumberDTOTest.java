package com.example.sim.dto.test;

import jakarta.validation.*;
import org.junit.jupiter.api.*;

import com.example.sim.dto.PhoneNumberDTO;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PhoneNumberDTOTest {

    private Validator validator;

    @BeforeEach
    void setupValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testGetterAndSetter() {
        PhoneNumberDTO dto = new PhoneNumberDTO();
        dto.setPhoneNumber("9876543210");
        assertEquals("9876543210", dto.getPhoneNumber());
    }

    @Test
    void testValidPhoneNumber() {
        PhoneNumberDTO dto = new PhoneNumberDTO();
        dto.setPhoneNumber("9876543210");

        Set<ConstraintViolation<PhoneNumberDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Expected no validation errors");
    }

    @Test
    void testBlankPhoneNumber() {
        PhoneNumberDTO dto = new PhoneNumberDTO();
        dto.setPhoneNumber("");

        Set<ConstraintViolation<PhoneNumberDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty(), "Expected validation error for blank phone number");

        boolean hasNotBlankMessage = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("phoneNumber"));
        assertTrue(hasNotBlankMessage);
    }

    @Test
    void testInvalidPhoneNumberPattern() {
        PhoneNumberDTO dto = new PhoneNumberDTO();
        dto.setPhoneNumber("1234567890"); // Starts with 1–5, invalid

        Set<ConstraintViolation<PhoneNumberDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty(), "Expected validation error for invalid pattern");

        boolean hasPatternMessage = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("phoneNumber"));
        assertTrue(hasPatternMessage);
    }
}