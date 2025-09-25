package com.example.sim.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.sim.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	   Optional<Customer> findByAadharAndPhoneNumberAndDob(String aadhar, String phoneNumber, String dob);
		
		
			
	 Optional<Customer> findByPhoneNumber(String phoneNumber);

	 List<Customer> findByAadhar(String aadhar);

}
