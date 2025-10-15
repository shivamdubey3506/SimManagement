package com.example.sim.service.impl;


import com.example.sim.dto.PhoneNumberDTO;
import com.example.sim.model.RecordHistory;
import com.example.sim.repository.RecordHistoryRepository;
import com.example.sim.service.RecordHistoryServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecordHistoryServiceImplTest {

    @InjectMocks
    private RecordHistoryServiceImpl recordHistoryService;

    @Mock
    private RecordHistoryRepository recordHistoryRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetByPhoneNumber_returnsMatchingRecords() {
        PhoneNumberDTO dto = new PhoneNumberDTO();
        dto.setPhoneNumber("9876543210");

        RecordHistory history1 = new RecordHistory();
        history1.setPhoneNumber("9876543210");
        history1.setOldStatus("pending");
        history1.setNewStatus("activated");
        history1.setChangedAt(LocalDateTime.now());
        history1.setChangedBy("Admin");

        RecordHistory history2 = new RecordHistory();
        history2.setPhoneNumber("9876543210");
        history2.setOldStatus("activated");
        history2.setNewStatus("deactivated");
        history2.setChangedAt(LocalDateTime.now());
        history2.setChangedBy("System");

        when(recordHistoryRepository.findByPhoneNumber("9876543210"))
                .thenReturn(List.of(history1, history2));

        List<RecordHistory> result = recordHistoryService.getByPhoneNumber(dto);

        assertEquals(2, result.size());
        assertEquals("pending", result.get(0).getOldStatus());
        assertEquals("deactivated", result.get(1).getNewStatus());
    }

    @Test
    void testGetByPhoneNumber_returnsEmptyList() {
        PhoneNumberDTO dto = new PhoneNumberDTO();
        dto.setPhoneNumber("0000000000");

        when(recordHistoryRepository.findByPhoneNumber("0000000000"))
                .thenReturn(List.of());

        List<RecordHistory> result = recordHistoryService.getByPhoneNumber(dto);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetAllHistory_returnsAllRecords() {
        RecordHistory history = new RecordHistory();
        history.setPhoneNumber("9876543210");
        history.setOldStatus("pending");
        history.setNewStatus("activated");
        history.setChangedAt(LocalDateTime.now());
        history.setChangedBy("Admin");

        when(recordHistoryRepository.findAll()).thenReturn(List.of(history));

        List<RecordHistory> result = recordHistoryService.getAllHistory();

        assertEquals(1, result.size());
        assertEquals("activated", result.get(0).getNewStatus());
    }

    @Test
    void testGetAllHistory_returnsEmptyList() {
        when(recordHistoryRepository.findAll()).thenReturn(List.of());

        List<RecordHistory> result = recordHistoryService.getAllHistory();
        assertTrue(result.isEmpty());
    }
}