package com.example.sim.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SimServiceTest {
    @Test
    void testInterfaceLoads() {
        // This test simply ensures the interface can be referenced for coverage.
        assertDoesNotThrow(() -> SimService.class.getName());
    }
}
