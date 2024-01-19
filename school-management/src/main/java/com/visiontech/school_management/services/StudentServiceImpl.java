package com.visiontech.school_management.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.visiontech.school_management.entites.Students;
import com.visiontech.school_management.entites.dtos.StudentsDTO;
import com.visiontech.school_management.repos.StudentsRepo;
import com.visiontech.school_management.services.interfaces.StudentsService;

import jakarta.transaction.Transactional;

@Service
public class StudentServiceImpl implements StudentsService {

	@Autowired
	private StudentsRepo studentsRepo;

	@Override
	public List<Students> getAllStudents() {
		return studentsRepo.findAll();
	}

	@Override
	public Optional<Students> getStudentByRollNo(Long rollNo) throws Exception {
		Optional<Students> std = studentsRepo.findByRollNo(rollNo);
		return std;
	}

	@Override
	public Students createStudent(StudentsDTO stdDto) {
		Students std = new Students();
		std.setRollNo(stdDto.getRollNo());
		std.setFirstName(stdDto.getFirstName());
		std.setLastName(stdDto.getLastName());
		std.setEmail(stdDto.getEmail());
		std.setGender(stdDto.getGender());
		return studentsRepo.save(std);
	}

	@Override
	public Students editStudent(Long rollNo,StudentsDTO stdDto){
		Students std = studentsRepo.findByRollNo(rollNo).get();
		if (std != null) {
			std.setRollNo(stdDto.getRollNo());
			std.setFirstName(stdDto.getFirstName());
			std.setLastName(stdDto.getLastName());
			std.setEmail(stdDto.getEmail());
			std.setGender(stdDto.getGender());

			return studentsRepo.save(std);
		}
		return null;
	}
	
	@Override
	public StudentsDTO updateStudent(Long rollNo) {
		Students std = studentsRepo.findByRollNo(rollNo).get();
		StudentsDTO stddto=new StudentsDTO();
		stddto.setRollNo(std.getRollNo());
		stddto.setFirstName(std.getFirstName());
		stddto.setLastName(std.getLastName());
		stddto.setGender(std.getGender());
		stddto.setEmail(std.getEmail());
		return stddto;
	}

	@Override
	@Transactional
	public String deleteStudentByRollNo(Long rollNo) {
		studentsRepo.deleteByRollNo(rollNo);
		return "Sucessfully Deleted";
	}

}
