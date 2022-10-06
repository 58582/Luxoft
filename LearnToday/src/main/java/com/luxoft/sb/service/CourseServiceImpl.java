package com.luxoft.sb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luxoft.sb.dao.CourseRepoistory;
import com.luxoft.sb.entity.Course;
import com.luxoft.sb.exception.ResourceNotFoundException;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepoistory courseRepo;

	@Override
	public Course saveCourse(Course course) {
		return courseRepo.save(course);
	}

	@Override
	public List<Course> getAllCourses() {
		List<Course> listOfCourses = courseRepo.findAll();
		return listOfCourses;
	}

	@Override
	public Course getCourseById(int courseId) {
		Course course = courseRepo.findById(courseId).orElseThrow(
				() -> new ResourceNotFoundException
				("Searched Data not Found with courseId " + courseId + ""));
		return course;
	}
	

}

