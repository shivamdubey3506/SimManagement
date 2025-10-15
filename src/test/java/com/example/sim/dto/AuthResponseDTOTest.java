package com.example.sim.dto;

import org.junit.jupiter.api.Test;

import com.example.sim.dto.AuthResponseDTO;

import static org.junit.jupiter.api.Assertions.*;

class AuthResponseDTOTest {
    @Test
    void testConstructorAndGetterSetter() {
        AuthResponseDTO dto = new AuthResponseDTO("token123");
        assertEquals("token123", dto.getToken());
        dto.setToken("newtoken");
        assertEquals("newtoken", dto.getToken());
    }
}
