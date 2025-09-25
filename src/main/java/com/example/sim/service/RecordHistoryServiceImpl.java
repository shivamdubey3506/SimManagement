package com.example.sim.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.sim.dto.PhoneNumberDTO;
import com.example.sim.model.RecordHistory;
import com.example.sim.repository.RecordHistoryRepository;
import jakarta.validation.Valid;

@Service
public class RecordHistoryServiceImpl implements RecordHistoryService {

	@Autowired
	private RecordHistoryRepository recordHistoryRepository;
	
	
	@Override
	public List<RecordHistory> getByPhoneNumber(@Valid PhoneNumberDTO phoneNumberDTO) {
		
		List<RecordHistory> list = recordHistoryRepository.findByPhoneNumber(phoneNumberDTO.getPhoneNumber());
		return list;
	}


	@Override
	public List<RecordHistory> getAllHistory() {
		
		List<RecordHistory> all= recordHistoryRepository.findAll();
		return all;
	}

}
