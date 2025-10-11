package com.example.sim.model.test;

import org.junit.jupiter.api.Test;

import com.example.sim.model.Customer;
import com.example.sim.model.Sim;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void testGetterAndSetter() {
        Customer customer = new Customer();
        Sim sim = new Sim();
        sim.setId(101L);
        sim.setSimNumber("SIM9876543210");
        sim.setStatus("activated");

        customer.setId(1L);
        customer.setName("John Doe");
        customer.setDob("1990-01-01");
        customer.setAadhar("123456789012");
        customer.setAddress("123 Main Street");
        customer.setPhoneNumber("9876543210");
        customer.setFatherName("Robert Doe");
        customer.setSim(sim);

        assertEquals(1L, customer.getId());
        assertEquals("John Doe", customer.getName());
        assertEquals("1990-01-01", customer.getDob());
        assertEquals("123456789012", customer.getAadhar());
        assertEquals("123 Main Street", customer.getAddress());
        assertEquals("9876543210", customer.getPhoneNumber());
        assertEquals("Robert Doe", customer.getFatherName());
        assertEquals(sim, customer.getSim());
        assertEquals(101L, customer.getSim().getId());
        assertEquals("SIM9876543210", customer.getSim().getSimNumber());
        assertEquals("activated", customer.getSim().getStatus());
    }
}