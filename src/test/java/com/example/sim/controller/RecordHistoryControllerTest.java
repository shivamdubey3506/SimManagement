package com.example.sim.controller;


import com.example.sim.controller.RecordHistoryController;
import com.example.sim.dto.PhoneNumberDTO;
import com.example.sim.model.RecordHistory;
import com.example.sim.service.RecordHistoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecordHistoryControllerTest {

    @Mock
    private RecordHistoryService recordHistoryService;

    @InjectMocks
    private RecordHistoryController recordHistoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetRecordHistoryByPhoneNumber_shouldReturnList() {
        PhoneNumberDTO phoneNumberDTO = new PhoneNumberDTO();
        phoneNumberDTO.setPhoneNumber("9876543210");

        RecordHistory record = new RecordHistory();
        List<RecordHistory> mockList = List.of(record);

        when(recordHistoryService.getByPhoneNumber(phoneNumberDTO)).thenReturn(mockList);

        ResponseEntity<?> response = recordHistoryController.getRecordHistoryByPhoneNumber(phoneNumberDTO);

        assertEquals(mockList, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(recordHistoryService).getByPhoneNumber(phoneNumberDTO);
    }

    @Test
    void testGetRecordHistoryByPhoneNumber_shouldReturnNoRecordFound() {
        PhoneNumberDTO phoneNumberDTO = new PhoneNumberDTO();
        phoneNumberDTO.setPhoneNumber("0000000000");

        when(recordHistoryService.getByPhoneNumber(phoneNumberDTO)).thenReturn(Collections.emptyList());

        ResponseEntity<?> response = recordHistoryController.getRecordHistoryByPhoneNumber(phoneNumberDTO);

        assertEquals("No Record Found", response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(recordHistoryService).getByPhoneNumber(phoneNumberDTO);
    }

    @Test
    void testGetAllRecordHistory_shouldReturnList() {
        RecordHistory record = new RecordHistory();
        List<RecordHistory> mockList = List.of(record);

        when(recordHistoryService.getAllHistory()).thenReturn(mockList);

        ResponseEntity<?> response = recordHistoryController.getAllRecordHistory();

        assertEquals(mockList, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(recordHistoryService).getAllHistory();
    }

    @Test
    void testGetAllRecordHistory_shouldReturnNoRecordFound() {
        when(recordHistoryService.getAllHistory()).thenReturn(Collections.emptyList());

        ResponseEntity<?> response = recordHistoryController.getAllRecordHistory();

        assertEquals("No Record Found", response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(recordHistoryService).getAllHistory();
    }
}