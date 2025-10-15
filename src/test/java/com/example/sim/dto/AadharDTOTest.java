package com.example.sim.dto;

import jakarta.validation.*;
import org.junit.jupiter.api.*;

import com.example.sim.dto.AadharDTO;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AadharDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testGetterAndSetter() {
        AadharDTO dto = new AadharDTO();
        dto.setAadhar("123456789012");
        assertEquals("123456789012", dto.getAadhar());
    }

    @Test
    void testValidAadhar() {
        AadharDTO dto = new AadharDTO();
        dto.setAadhar("123456789012");

        Set<ConstraintViolation<AadharDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Expected no validation errors");
    }

    @Test
    void testBlankAadhar() {
        AadharDTO dto = new AadharDTO();
        dto.setAadhar("");

        Set<ConstraintViolation<AadharDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty(), "Expected validation error for blank aadhar");

        boolean hasNotBlankMessage = violations.stream()
                .anyMatch(v -> v.getMessageTemplate().contains("customer.aadhar.notblank"));
        assertTrue(hasNotBlankMessage, "Expected @NotBlank violation message");
    }

    @Test
    void testShortAadhar() {
        AadharDTO dto = new AadharDTO();
        dto.setAadhar("12345");

        Set<ConstraintViolation<AadharDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty(), "Expected validation error for short aadhar");

        boolean hasSizeMessage = violations.stream()
                .anyMatch(v -> v.getMessageTemplate().contains("customer .aadhar.size"));
        assertTrue(hasSizeMessage, "Expected @Size violation message");
    }

    @Test
    void testLongAadhar() {
        AadharDTO dto = new AadharDTO();
        dto.setAadhar("123456789012345");

        Set<ConstraintViolation<AadharDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty(), "Expected validation error for long aadhar");

        boolean hasSizeMessage = violations.stream()
                .anyMatch(v -> v.getMessageTemplate().contains("customer .aadhar.size"));
        assertTrue(hasSizeMessage, "Expected @Size violation message");
    }
}
