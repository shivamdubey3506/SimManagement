package com.example.sim.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sim.model.RecordHistory;

public interface RecordHistoryRepository extends JpaRepository<RecordHistory, Long> {

	List<RecordHistory> findByPhoneNumber(String phoneNumber);

	

}
