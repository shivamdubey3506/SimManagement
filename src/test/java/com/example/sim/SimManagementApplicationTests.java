package com.example.sim;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SimManagementApplicationTest {

    @Test
    void contextLoads() {
        // This test ensures that the Spring application context loads without errors.
    }

    @Test
    void mainMethodRuns() {
        SimManagementApplication.main(new String[]{});
        // This test ensures that the main method runs without exceptions.
    }
}