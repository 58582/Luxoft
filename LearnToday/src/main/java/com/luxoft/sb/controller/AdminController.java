package com.luxoft.sb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luxoft.sb.entity.Course;
import com.luxoft.sb.exception.ResourceNotFoundException;
import com.luxoft.sb.service.CourseService;

@RestController
@RequestMapping("/api/Admin")
public class AdminController {
	@Autowired
	private CourseService courseService;

	@PostMapping("/saveCourse")
	public ResponseEntity<Course> saveCourse(@RequestBody Course course) {
		Course savedCourse = courseService.saveCourse(course);
		return new ResponseEntity<Course>(savedCourse, HttpStatus.CREATED);
	}

	//@PreAuthorize("hasAnyRole('ADMIN','STUDENT')")
	@GetMapping
	public List<Course> getAllCourses() {
		return courseService.getAllCourses();
	}

	@GetMapping("{courseId}")
	public ResponseEntity getCourseById(@PathVariable("courseId") int courseId) {
		try {
			return new ResponseEntity(courseService.getCourseById(courseId), HttpStatus.OK);
		} catch (ResourceNotFoundException resourceNotFoundException) {
			return new ResponseEntity(resourceNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
