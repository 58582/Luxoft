package com.luxoft.sb.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luxoft.sb.entity.Course;
import com.luxoft.sb.entity.Student;
import com.luxoft.sb.exception.ResourceExistsException;
import com.luxoft.sb.exception.ResourceNotFoundException;
import com.luxoft.sb.service.CourseService;

class AdminControllerTest {
	@Mock
	private CourseService courseService;
	
	@InjectMocks
	private AdminController adminController;
	
	private MockMvc mockMvc;
	
	Course course = new Course(1,"DBMS course",2000f,"DS for bignners","David",
			   new Date(), new ArrayList<Student>());
	Course course1 = new Course("DBMS course",2000f,"DS for bignners","David",
			   new Date(), new ArrayList<Student>());
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
	}
	
	private String mapToJson(Object object) throws JsonProcessingException{
		ObjectMapper objMap = new ObjectMapper();
		return objMap.writeValueAsString(object);
	}

	@Test
	void testSaveCourse() throws JsonProcessingException,Exception {
		Mockito.when(courseService.saveCourse(any())).thenReturn(course);
		String content = mapToJson(course);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/Admin/saveCourse").content(content).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(status().isCreated());
	}
	
	@Test
	void testSaveCourseException() throws Exception {
		Mockito.when(courseService.saveCourse(any())).thenThrow(ResourceExistsException.class);
		String content = mapToJson(course);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/Admin/saveCourse").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(content);
		mockMvc.perform(requestBuilder).andExpect(status().isAlreadyReported());
	}

	@Test
	void testGetAllCourses() throws Exception {
		List<Course> courses = new ArrayList<>();
		courses.add(course);
		courses.add(course1);
		Mockito.when(courseService.getAllCourses()).thenReturn(courses);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/Admin").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(status().isOk());
	}

	@Test
	void testGetCourseById() throws Exception {
		Mockito.when(courseService.getCourseById(anyInt())).thenReturn(course);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/Admin/1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(status().isOk());
	}
	
	@Test
	void testGetCourseByIdException() throws Exception{
		Mockito.when(courseService.getCourseById(anyInt())).thenThrow(ResourceNotFoundException.class);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/Admin/1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(status().isNotFound());
	}

}
