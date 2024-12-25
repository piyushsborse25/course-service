package com.cs.courses.courseservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cs.courses.courseservice.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

}
