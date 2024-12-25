package com.cs.courses.courseservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cs.courses.courseservice.entity.CourseTags;
import com.cs.courses.courseservice.entity.CourseTagsPk;

public interface CourseTagsRepository extends JpaRepository<CourseTags, CourseTagsPk> {

	List<CourseTags> findByTagsPk_Course_Cid(int cid);
}
