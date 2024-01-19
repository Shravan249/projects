package com.visiontech.school_management.repos;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.visiontech.school_management.entites.Teachers;


public interface TeachersRepo extends JpaRepository<Teachers, Long> {

	Optional<Teachers> findByTid(Long tid);

	void deleteByTid(Long tid);
}
