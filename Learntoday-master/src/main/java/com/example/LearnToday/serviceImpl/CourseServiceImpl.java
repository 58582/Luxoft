package com.example.LearnToday.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LearnToday.Entity.Course;
import com.example.LearnToday.Repoistory.CourseRepoistory;
import com.example.LearnToday.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepoistory courseRepo;

	@Override
	public List<Course> getAllCourses() {
		// TODO Auto-generated method stub

		List<Course> list = courseRepo.findAll();

		return list;

	}

	@Override
	public Course getCourseById(int courseId) {

		Course course = courseRepo.findById(courseId).get();

		return course;
	}

	@Override
	public int saveCourse(Course course) {
		
		return courseRepo.save(course).getId();
	}

}
