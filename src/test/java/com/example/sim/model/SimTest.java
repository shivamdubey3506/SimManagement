package com.example.sim.model;


import org.junit.jupiter.api.Test;

import com.example.sim.model.Customer;
import com.example.sim.model.Sim;

import static org.junit.jupiter.api.Assertions.*;

class SimTest {

    @Test
    void testGetterAndSetter() {
        Sim sim = new Sim();
        Customer  customer = new Customer();
        customer.setId(101L);
        customer.setName("John Doe");

        sim.setId(1L);
        sim.setSimNumber("SIM9876543210");
        sim.setStatus("activated");
        sim.setCustomer(customer);

        assertEquals(1L, sim.getId());
        assertEquals("SIM9876543210", sim.getSimNumber());
        assertEquals("activated", sim.getStatus());
        assertEquals(customer, sim.getCustomer());
        assertEquals(101L, sim.getCustomer().getId());
        assertEquals("John Doe", sim.getCustomer().getName());
    }
}