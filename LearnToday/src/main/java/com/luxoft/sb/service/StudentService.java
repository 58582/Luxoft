package com.luxoft.sb.service;

import com.luxoft.sb.entity.Student;

public interface StudentService {
	Student enrollStudent(Student student);
	void deleteByEnrollId(int enrollmentId);
}
