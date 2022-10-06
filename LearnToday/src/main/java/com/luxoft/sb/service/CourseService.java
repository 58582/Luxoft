package com.luxoft.sb.service;

import java.util.List;

import com.luxoft.sb.entity.Course;

public interface CourseService {
	Course saveCourse(Course course);
	List<Course> getAllCourses();
	Course getCourseById(int courseId);
}
