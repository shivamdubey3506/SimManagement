package com.example.sim.utility;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {
    @Test
    void testGenerateAndValidateToken() {
        JwtUtil jwtUtil = new JwtUtil();
        String aadhaar = "123456789012";
        String token = jwtUtil.generateToken(aadhaar);
        assertNotNull(token);
        assertTrue(jwtUtil.validateToken(token));
        assertEquals(aadhaar, jwtUtil.extractAadhaar(token));
    }

    @Test
    void testValidateToken_invalidToken() {
        JwtUtil jwtUtil = new JwtUtil();
        String invalidToken = "invalid.token.value";
        assertFalse(jwtUtil.validateToken(invalidToken));
    }
}
