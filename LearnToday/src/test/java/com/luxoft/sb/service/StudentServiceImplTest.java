package com.luxoft.sb.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.util.ArrayList;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.luxoft.sb.dao.StudentRepository;
import com.luxoft.sb.entity.Course;
import com.luxoft.sb.entity.Student;
import com.luxoft.sb.exception.ResourceNotFoundException;

class StudentServiceImplTest {
	
	@InjectMocks
	private StudentServiceImpl studentService;
	
	@Mock
	private StudentRepository studentRepo;
	
	private MockMvc mockMvc;
	
	Course course = new Course(1,"DBMS course",2000f,"DS for bignners","David",
			   new Date(), new ArrayList<Student>());
	Student student = new Student(101,101,course);
	
	@BeforeEach
	void setUpBeforeClass() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(studentService).build();
	}
	
	@Test
	void testEnrollStudent() {
		Mockito.when(studentRepo.save(student)).thenReturn(student);
		Student studentEnroll = studentService.enrollStudent(student);
		assertEquals(student,studentEnroll);
	}

	@Test
	void testDeleteByEnrollId() {
		Mockito.when(studentRepo.save(student)).thenReturn(student);
		studentRepo.delete(student);
		verify(studentRepo,times(1)).delete(student);
	}
	
	@Test
	void testDeleteStudentException() {
		Mockito.when(studentRepo.findById(anyInt())).thenThrow(ResourceNotFoundException.class);		
		try{
			studentService.deleteByEnrollId(1);
		}catch(Exception e) {
			assertEquals(ResourceNotFoundException.class,e.getClass());
		}
	}

}
