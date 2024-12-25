package com.cs.courses.courseservice.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseTagsPk {

	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CID", referencedColumnName = "CID")
	private Course course;

	@Column(name = "EXT_TAGS")
	private String extraTags;

}
