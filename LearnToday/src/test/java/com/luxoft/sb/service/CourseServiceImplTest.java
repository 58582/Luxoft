package com.luxoft.sb.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.luxoft.sb.dao.CourseRepoistory;
import com.luxoft.sb.entity.Course;
import com.luxoft.sb.entity.Student;
import com.luxoft.sb.exception.ResourceNotFoundException;

class CourseServiceImplTest {

	@InjectMocks
	private CourseServiceImpl courseService;
	
	@Mock
	private CourseRepoistory courseRepo;
	
	private MockMvc mockMvc;
	
	@BeforeEach
	void setUpBeforeClass() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(courseService).build();
	}

	@Test
	void testSaveCourse() {
		Course course = new Course(1,"DBMS course",2000f,"DS for bignners","David",
				   new Date(), new ArrayList<Student>());
		Course course1 = new Course("DBMS course",2000f,"DS for bignners","David",
				   new Date(), new ArrayList<Student>());
		Mockito.when(courseRepo.save(any())).thenReturn(course);
		Course savedCourse = courseService.saveCourse(course1);
		assertEquals(course.getCourseId(),savedCourse.getCourseId());
	}

	@Test
	void testGetAllCourses() {
		List<Course> courseList = new ArrayList<>();
		courseList.add(new Course(1,"DS", 145f,"new DS Course","Jio",new Date(),new ArrayList<Student>()));
		Mockito.when(courseRepo.findAll()).thenReturn(courseList);
		List<Course> resCourseList= courseService.getAllCourses();
		assertEquals(courseService.getAllCourses(),resCourseList);
	}

	@Test
	void testGetCourseById() {
		Optional<Course> course =Optional.of(new Course(1,  "DS", 145f,"new DS Course","Jio",new Date(),new ArrayList<Student>()));
		Mockito.when(courseRepo.findById(anyInt())).thenReturn(course);
		Course resData = courseService.getCourseById(1);
		assertEquals(1,resData.getCourseId());
	}
	
	@Test
	void testGetCourseByIdException() {
		Mockito.when(courseRepo.findById(anyInt())).thenThrow(ResourceNotFoundException.class);
		try {
			courseService.getCourseById(1);
		}catch(Exception e) {
			assertEquals(ResourceNotFoundException.class,e.getClass());
		}
	}

}
