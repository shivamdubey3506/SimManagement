package com.example.sim.repository;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.sim.model.Sim;

public interface SimRepository extends JpaRepository <Sim ,Long> {

	Optional<Sim> findBySimNumber(String simNumber);

	//public static Optional<Sim> finById(Long id) 
		
		
	

}
