package com.example.LearnToday.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LearnToday.Entity.Course;
import com.example.LearnToday.Entity.Student;
import com.example.LearnToday.Repoistory.CourseRepoistory;
import com.example.LearnToday.Repoistory.StudentRepoistory;
import com.example.LearnToday.payload.StudentDto;
import com.example.LearnToday.service.studentService;

@Service
public class StudentServiceImpl implements studentService {

	@Autowired
	private StudentRepoistory repo;

	@Autowired
	private CourseRepoistory courseRepo;

	private ModelMapper mapper;

	@Override
	public Student enrollStudent(int id,StudentDto std) {

		//Integer id = std.getCourseId();
		
		Student studentData = mapper.map(std, Student.class);

		Course course = courseRepo.findById(id).get();
		
		studentData.setCourse(course);
		
		

//		Student student = new Student();
//
//		student.setEnrollmentId(std.getEnrollmentId());
//		student.setCourse(course);
//		student.setStudentId(std.getStudentId());
		//student.setCourse_data(std.getCourseId());

		Student student = repo.save(studentData);

		return student;
	}

	@Override
	public void deleteByEnrollId(int enrollmentId) {
		
		repo.deleteById(enrollmentId);
		
	}

	@Override
	public int saveStudent(Student std) {
		
		
		return repo.save(std).getStudentId();
	}

}
