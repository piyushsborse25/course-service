package com.cs.courses.courseservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "COURSE")
@AllArgsConstructor
@NoArgsConstructor
public class Course {

	@Id
	@Column(name = "CID")
	private int cid;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "DESC")
	private String desc;

	@Column(name = "TYPE")
	private String type;

	@Column(name = "LESS_COUNT")
	private String lessonCount;

	@Column(name = "SUBS_TYPE")
	private String subscription;

}
