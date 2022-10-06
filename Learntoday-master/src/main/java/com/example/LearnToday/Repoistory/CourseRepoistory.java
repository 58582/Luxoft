package com.example.LearnToday.Repoistory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.LearnToday.Entity.Course;

public interface CourseRepoistory extends JpaRepository<Course, Integer> {

}
