package com.example.LearnToday.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.LearnToday.Entity.Course;
import com.example.LearnToday.Entity.Student;
import com.example.LearnToday.Repoistory.StudentRepoistory;
import com.example.LearnToday.payload.StudentDto;
import com.example.LearnToday.service.studentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentRepoistory repo;
	
	@Autowired
	private studentService dataService;

	
//	@PreAuthorize("hasRole('ADMIN')")
//	@GetMapping("/api/getallcourses")
//	public List<Course> getAllCourse() {
//		List<Course> list = repo.findAllCourses();
//		return list;
//
//	}
	
//	@PostMapping("/api/{id}/students")
//	public ResponseEntity<Student> enrollStudentCourse(@PathVariable int id,@RequestBody StudentDto std){
//		
//		System.out.println("std"+ std);
//		
//		Student student =dataService.enrollStudent(id,std);
//		
//		System.out.println("student"+ student);
//		
//		return ResponseEntity.ok(student);
//		
//		
//	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/students")
	public ResponseEntity<String> createStudent(@RequestBody Student std){
		
		System.out.println("std"+ std);
		
		int data =dataService.saveStudent(std);
		
		//System.out.println("student"+ student);
		
		String str = "created" + data;
		
		
		
		return ResponseEntity.ok(str);
		
		
	}
	
	@PreAuthorize("hasRole('STUDENT')")
	@DeleteMapping("/delete/{enrollmentId}")
	public ResponseEntity<String> deleteStudentByEnrollmentId(@PathVariable int enrollmentId){
		
		dataService.deleteByEnrollId(enrollmentId);
		String msg =" id successfully deleted" + enrollmentId;
		
		return ResponseEntity.ok(msg);
		
		
	}
	
	
}
