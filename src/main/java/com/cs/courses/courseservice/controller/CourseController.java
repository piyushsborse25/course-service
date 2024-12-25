package com.cs.courses.courseservice.controller;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cs.courses.courseservice.bean.CourseResponse;
import com.cs.courses.courseservice.bean.GenericResponse;
import com.cs.courses.courseservice.entity.Course;
import com.cs.courses.courseservice.entity.CourseImgUrl;
import com.cs.courses.courseservice.entity.CourseImgUrlPk;
import com.cs.courses.courseservice.repository.CourseImgUrlRepository;
import com.cs.courses.courseservice.repository.CourseRepository;
import com.cs.courses.courseservice.repository.CourseTagsRepository;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class CourseController {

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	CourseImgUrlRepository imgUrlRepository;

	@Autowired
	CourseTagsRepository tagsRepository;

	@GetMapping("/")
	public String welcome() {
		return "Welcome to Course Services";
	}

	@GetMapping("/courses")
	@Transactional
	public List<CourseResponse> getCourses(HttpServletResponse httpServletResponse) {

		List<CourseResponse> response = new ArrayList<>();

		List<Course> data = courseRepository.findAll();
		for (Course course : data) {
			int cid = course.getCid();
			CourseImgUrl url = imgUrlRepository.findByImgUrlPk_Course_Cid(cid);

			String imgUrl = "";
			if (url != null && url.getImgUrlPk() != null) {
				imgUrl = url.getImgUrlPk().getImgUrl();
			}

			List<String> extraTags = tagsRepository.findByTagsPk_Course_Cid(cid).stream()
					.map(item -> item.getTagsPk().getExtraTags()).collect(Collectors.toList());
			response.add(new CourseResponse(cid, course.getTitle(), course.getDesc(), course.getType(),
					course.getLessonCount(), course.getSubscription(), imgUrl, extraTags));
		}

		return response;
	}

	@GetMapping("/course")
	public CourseResponse getCourse(HttpServletResponse httpServletResponse, @RequestParam("cid") Integer cid) {

		Optional<Course> data = courseRepository.findById(cid);
		Course course = data.get();
		CourseImgUrl url = imgUrlRepository.findByImgUrlPk_Course_Cid(cid);

		String imgUrl = "";
		if (url != null && url.getImgUrlPk() != null) {
			imgUrl = url.getImgUrlPk().getImgUrl();
		}

		List<String> extraTags = tagsRepository.findByTagsPk_Course_Cid(cid).stream()
				.map(item -> item.getTagsPk().getExtraTags()).collect(Collectors.toList());
		CourseResponse courseResponse = new CourseResponse(cid, course.getTitle(), course.getDesc(), course.getType(),
				course.getLessonCount(), course.getSubscription(), imgUrl, extraTags);

		return courseResponse;
	}

	@PutMapping("/course")
	public GenericResponse getCourse(HttpServletResponse httpServletResponse, @RequestBody CourseResponse body) {

		Course course = new Course(body.getCid(), body.getTitle(), body.getDescription(), body.getType(),
				body.getLessonCount(), body.getSubscription());
		try {
			courseRepository.save(course);
			imgUrlRepository.save(new CourseImgUrl(new CourseImgUrlPk(course, body.getImageURI())));
		} catch (Exception e) {
			System.out.println(e);
		}

		return new GenericResponse("Product Updated successfully");
	}

	@PostMapping("/course")
	public GenericResponse createCourse(HttpServletResponse httpServletResponse, @RequestBody CourseResponse body) {

		Course course = new Course((int) (courseRepository.count() + 10), body.getTitle(), body.getDescription(),
				body.getType(), body.getLessonCount(), body.getSubscription());
		try {
			courseRepository.save(course);
			imgUrlRepository.save(new CourseImgUrl(new CourseImgUrlPk(course, body.getImageURI())));
		} catch (Exception e) {
			System.out.println(e);
		}

		return new GenericResponse("Product Created successfully");
	}

	@DeleteMapping("/course/{cid}")
	public GenericResponse deleteCourse(HttpServletResponse httpServletResponse, @PathVariable("cid") Integer cid) {

		try {
			Optional<Course> course = courseRepository.findById(cid);
			
			courseRepository.deleteById(cid);
			CourseImgUrl url = imgUrlRepository.findByImgUrlPk_Course_Cid(cid);

			String imgUrl = "";
			if (url != null && url.getImgUrlPk() != null) {
				imgUrl = url.getImgUrlPk().getImgUrl();
			}
			
			imgUrlRepository.deleteById(new CourseImgUrlPk(course.get(), imgUrl));
			
		} catch (Exception e) {
			System.out.println(e);
		}

		return new GenericResponse("Product Deleted successfully");
	}
}
