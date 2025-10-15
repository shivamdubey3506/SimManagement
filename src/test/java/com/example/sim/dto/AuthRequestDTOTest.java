package com.example.sim.dto;

import org.junit.jupiter.api.Test;

import com.example.sim.dto.AuthRequestDTO;

import static org.junit.jupiter.api.Assertions.*;

class AuthRequestDTOTest {
    @Test
    void testGetterAndSetter() {
        AuthRequestDTO dto = new AuthRequestDTO();
        dto.setAadhar("123456789012");
        dto.setDob("1990-01-01");
        assertEquals("123456789012", dto.getAadhar());
        assertEquals("1990-01-01", dto.getDob());
    }
}
