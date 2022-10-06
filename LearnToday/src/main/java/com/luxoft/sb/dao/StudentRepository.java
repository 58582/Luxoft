package com.luxoft.sb.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.luxoft.sb.entity.Course;
import com.luxoft.sb.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{
	@Query("select course from Course course order by stDate asc")
	public List<Course> findAllCourses();
}
