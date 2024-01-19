package com.visiontech.school_management.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.visiontech.school_management.entites.Students;

public interface StudentsRepo extends JpaRepository<Students, Long> {

	Optional<Students> findByRollNo(Long rollNo);
	
	void deleteByRollNo(Long rollNo);
	
	
}
