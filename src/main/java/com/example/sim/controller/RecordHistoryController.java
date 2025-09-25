package com.example.sim.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.sim.dto.PhoneNumberDTO;
import com.example.sim.model.RecordHistory;
import com.example.sim.service.RecordHistoryService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/recordHistory")
public class RecordHistoryController {
	
	@Autowired
	private RecordHistoryService recordHistoryService;

	
	@PostMapping("/phonenumber")
	public ResponseEntity<?> getRecordHistoryByPhoneNumber(@RequestBody @Valid PhoneNumberDTO phoneNumberDTO){
		List<RecordHistory> list=recordHistoryService.getByPhoneNumber(phoneNumberDTO);
		if(list.isEmpty()) {
			return ResponseEntity.ok().body("No Record Found");
		}
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllRecordHistory(){
		List<RecordHistory> list=recordHistoryService.getAllHistory();
		if(list.isEmpty()) {
			return ResponseEntity.ok().body("No Record Found");
		}
		return ResponseEntity.ok(list);
	}
}
