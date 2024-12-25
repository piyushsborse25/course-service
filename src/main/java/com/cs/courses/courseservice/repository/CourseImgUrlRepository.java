package com.cs.courses.courseservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cs.courses.courseservice.entity.CourseImgUrl;
import com.cs.courses.courseservice.entity.CourseImgUrlPk;

public interface CourseImgUrlRepository extends JpaRepository<CourseImgUrl, CourseImgUrlPk> {

	CourseImgUrl findByImgUrlPk_Course_Cid(int cid);
}
