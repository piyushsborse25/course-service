package com.cs.courses.courseservice.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "COURSE_IMG_URL")
@AllArgsConstructor
@NoArgsConstructor
public class CourseImgUrl {

	@EmbeddedId
	private CourseImgUrlPk imgUrlPk;  

}
