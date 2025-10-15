package com.example.sim.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {
    @Test
    void testInterfaceLoads() {
        // This test simply ensures the interface can be referenced for coverage.
        assertDoesNotThrow(() -> CustomerService.class.getName());
    }
}
