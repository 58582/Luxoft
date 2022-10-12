package com.luxoft.sb.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.Date;
import static org.mockito.ArgumentMatchers.any;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luxoft.sb.entity.Course;
import com.luxoft.sb.entity.Student;
import com.luxoft.sb.exception.ResourceNotFoundException;
import com.luxoft.sb.service.CourseService;
import com.luxoft.sb.service.StudentService;

class StudentControllerTest {

	@Mock
	private CourseService courseService;
	
	@Mock
	private StudentService studentService;
	
	@InjectMocks
	private StudentController studentController;
	
	private MockMvc mockMvc;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
	}
	
	Course course = new Course(1,"DBMS course",2000f,"DS for bignners","David",
			   new Date(), new ArrayList<Student>());
	Course course1 = new Course("DBMS course",2000f,"DS for bignners","David",
			   new Date(), new ArrayList<Student>());
	Student student = new Student(1001,1001,course);
	
	private String mapToJson(Object object) throws JsonProcessingException{
		ObjectMapper objMap = new ObjectMapper();
		return objMap.writeValueAsString(object);
	}

	@Test
	void testPostStudent() throws Exception {
		Mockito.when(studentService.enrollStudent(any())).thenReturn(student);
		String content = mapToJson(student);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/Student").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(content);
		mockMvc.perform(requestBuilder).andExpect(status().isCreated());
	}

//	@Test
//	void testGetAllCourse() throws Exception {
//		List<Course> courseList= new ArrayList<>();
//		courseList.add(course);
//		courseList.add(course1);
//		Mockito.when(courseService.getAllCourses()).thenReturn(courseList);
//		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/Student").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
//		mockMvc.perform(requestBuilder).andExpect(status().isOk());	
//	}

	@Test
	void testDeleteStudentEnrollment() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/Student/1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(status().isOk());
	}
	
	@Test
	void testDeleteStudentEnrollmentException() {
		Mockito.doThrow(ResourceNotFoundException.class).when(studentService).deleteByEnrollId(anyInt());
		try {
			MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/Student/1")
					.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
			mockMvc.perform(requestBuilder);
		} catch (Exception e) {
			assertEquals(ResourceNotFoundException.class, e.getClass());
		}
	}

}
