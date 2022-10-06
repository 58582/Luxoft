package com.example.LearnToday.Repoistory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.LearnToday.Entity.Course;
import com.example.LearnToday.Entity.Student;


public interface StudentRepoistory extends JpaRepository<Student, Integer> {
	
	@Query("select course from Course course order by stDate asc")
	public List<Course> findAllCourses();
	

}
