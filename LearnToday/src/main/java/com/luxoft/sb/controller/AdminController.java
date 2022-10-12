package com.luxoft.sb.controller;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(value = "Admin controller exposes save,getAllCourses and getCourseById REST APIs")
@RestController
@RequestMapping("/api/Admin")
public class AdminController {
	@Autowired
	private CourseService courseService;
	
	private static final Log LOGGER = LogFactory.getLog(AdminController.class);

	@ApiOperation(value = "Save Course REST API")
	@PostMapping("/saveCourse")
	public ResponseEntity<Course> saveCourse(@RequestBody Course course) {
		Course savedCourse = courseService.saveCourse(course);
		LOGGER.info("Course Saved in Admin Controller Successfully");
		return new ResponseEntity<Course>(savedCourse, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Get All Courses REST API")
	@GetMapping
	public List<Course> getAllCourses() {
		LOGGER.info("getting All Course in Admin Controller");
		return courseService.getAllCourses();
	}

	@ApiOperation(value = "Get Course By Id REST API")
	@GetMapping("{courseId}")
	public ResponseEntity getCourseById(@PathVariable("courseId") int courseId) {
		try {
			LOGGER.info("getting CourseById in Admin Controller");
			return new ResponseEntity(courseService.getCourseById(courseId), HttpStatus.OK);
		} catch (ResourceNotFoundException resourceNotFoundException) {
			LOGGER.error("Exception happened getting CourseById in Admin Controller");
			return new ResponseEntity(resourceNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
