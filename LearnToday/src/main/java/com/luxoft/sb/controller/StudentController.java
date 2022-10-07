package com.luxoft.sb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luxoft.sb.dao.StudentRepository;
import com.luxoft.sb.entity.Course;
import com.luxoft.sb.entity.Student;
import com.luxoft.sb.service.StudentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/Student")
@Api(value = "CRUD Rest APIs for Student resources")
public class StudentController {
	@Autowired
	private StudentService studentService;
	@Autowired
	private StudentRepository studentRepo;
	
	@ApiOperation(value = "Post Student REST API")
	@PostMapping
	public ResponseEntity<Student> postStudent(@RequestBody Student student) {
		Student savedStudent;
		savedStudent= studentService.enrollStudent(student);
		return new ResponseEntity<Student>(savedStudent, HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Get All Courses REST API")
	@GetMapping
	public List<Course> getAllCourse() {
		List<Course> studentList = studentRepo.findAllCourses();
		return studentList;
	}
	
	@ApiOperation(value = "Delete  Student By Id REST API")
	@DeleteMapping("{enrollmentId}")
	public ResponseEntity<String> deleteStudentEnrollment(@PathVariable int enrollmentId) {	
		try {
			studentService.deleteByEnrollId(enrollmentId);
			return new ResponseEntity<String>("enrollmentId with "+enrollmentId+" deleted succesfully",HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<String>("No enrollmentId Info found with "+enrollmentId+"",HttpStatus.BAD_REQUEST);
		}
	}

}
