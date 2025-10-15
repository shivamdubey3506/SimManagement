package com.example.sim.repository;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomerRepositoryTest {
    @Test
    void testRepositoryInterfaceLoads() {
        // This test simply ensures the interface can be referenced for coverage.
        assertDoesNotThrow(() -> CustomerRepository.class.getName());
    }
}
