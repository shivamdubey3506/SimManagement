package com.example.sim.service;

import java.util.List;

import com.example.sim.dto.PhoneNumberDTO;
import com.example.sim.model.RecordHistory;

import jakarta.validation.Valid;

public interface RecordHistoryService {

	List<RecordHistory> getByPhoneNumber(@Valid PhoneNumberDTO phoneNumberDTO);

	List<RecordHistory> getAllHistory();

}
