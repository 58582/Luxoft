package com.example.LearnToday.service;

import com.example.LearnToday.Entity.Course;
import com.example.LearnToday.Entity.Student;
import com.example.LearnToday.payload.StudentDto;

public interface studentService {

	
	int saveStudent(Student std);
	
	Student enrollStudent(int id,StudentDto std);
    void deleteByEnrollId(int enrollmentId);
}
