package com.example.LearnToday.service;

import java.util.List;

import com.example.LearnToday.Entity.Course;

public interface CourseService {
	
	
	List<Course> getAllCourses();
	
	Course getCourseById(int courseId);
	
	int saveCourse(Course course);

}
