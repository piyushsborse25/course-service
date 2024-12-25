package com.cs.courses.courseservice.bean;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponse {

	private int cid;

	private String title;

	private String description;

	private String type;

	private String lessonCount;

	private String subscription;

	private String imageURI;

	private List<String> extraTags;
}
