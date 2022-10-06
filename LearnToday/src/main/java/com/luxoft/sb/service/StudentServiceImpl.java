package com.luxoft.sb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luxoft.sb.dao.StudentRepository;
import com.luxoft.sb.entity.Student;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepo;
	@Override
	public Student enrollStudent(Student student) {
		Student saved = null;
		try {
			saved = studentRepo.save(student);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return saved;
	}

	@Override
	public void deleteByEnrollId(int enrollmentId) {
		Student student  = studentRepo.findById(enrollmentId).get();
		studentRepo.delete(student);
	}

}
