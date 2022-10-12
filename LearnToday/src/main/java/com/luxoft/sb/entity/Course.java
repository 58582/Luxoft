package com.luxoft.sb.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "course")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int courseId;
	
	private String title;

	private float fees;

	private String description;

	private String trainer;

	private Date stDate;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "course")
	List<Student> students;

	public Course(String title, float fees, String description, String trainer, Date stDate, List<Student> students) {
		super();
		this.title = title;
		this.fees = fees;
		this.description = description;
		this.trainer = trainer;
		this.stDate = stDate;
		this.students = students;
	}
	
	
}
