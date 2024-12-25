package com.cs.courses.courseservice.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "COURSE_TAGS")
@AllArgsConstructor
@NoArgsConstructor
public class CourseTags {

	@EmbeddedId
	private CourseTagsPk tagsPk;

}
