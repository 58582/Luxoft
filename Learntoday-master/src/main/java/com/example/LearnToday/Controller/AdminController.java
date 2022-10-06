package com.example.LearnToday.Controller;

import java.util.List;import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.LearnToday.Entity.Course;
import com.example.LearnToday.service.CourseService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	private CourseService service;
	
	
	@PreAuthorize("hasAnyRole('ADMIN','STUDENT')")
	@GetMapping("/allCourses")
	public List<Course> getAllCourses(){
		
		return service.getAllCourses();
		
	}
	
	
	@PreAuthorize("hasAnyRole('STUDENT','ADMIN')")
	@GetMapping("/api/{courseId}")
	public ResponseEntity<Course> getCourseById(@PathVariable int courseId){
		
		Course course = service.getCourseById(courseId);
		return ResponseEntity.ok(course);
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/courses")
	public ResponseEntity<String> saveCourse(@RequestBody Course course){
		
		int id = service.saveCourse(course);
		String response = "course created"+ id;
		return ResponseEntity.ok(response);
	}
	

}
