package com.example.sim.model;


import org.junit.jupiter.api.Test;
import com.example.sim.model.RecordHistory;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class RecordHistoryTest {

    @Test
    void testGetterAndSetter() {
        RecordHistory history = new RecordHistory();

        Long id = 1L;
        String phoneNumber = "9876543210";
        String oldStatus = "pending";
        String newStatus = "activated";
        LocalDateTime changedAt = LocalDateTime.of(2025, 10, 10, 22, 30);
        String changedBy = "Admin";

        history.setId(id);
        history.setPhoneNumber(phoneNumber);
        history.setOldStatus(oldStatus);
        history.setNewStatus(newStatus);
        history.setChangedAt(changedAt);
        history.setChangedBy(changedBy);

        assertEquals(id, history.getId());
        assertEquals(phoneNumber, history.getPhoneNumber());
        assertEquals(oldStatus, history.getOldStatus());
        assertEquals(newStatus, history.getNewStatus());
        assertEquals(changedAt, history.getChangedAt());
        assertEquals(changedBy, history.getChangedBy());
    }
}
