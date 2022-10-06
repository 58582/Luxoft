package com.luxoft.sb.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luxoft.sb.entity.Course;

public interface CourseRepoistory extends JpaRepository<Course, Integer>{

}
