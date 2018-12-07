package com.turchanovskyi.virtual_university.controllers;

import com.turchanovskyi.virtual_university.interfaces.CourseService;
import com.turchanovskyi.virtual_university.model.Course;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/course")
public class CourseController {

	private final CourseService courseService;

	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}

	@GetMapping
	public Iterable<Course> main()
	{
		return courseService.findAll();
	}

	@GetMapping("/{courseId}")
	public Course getCourse(@PathVariable Long courseId)
	{
		return courseService.findById(courseId);
	}

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public Course createCourse(@RequestBody Course course)
	{
		course.setCourse_id(null);

		courseService.save(course);

		return course;
	}

	@PutMapping("/update")
	@ResponseStatus(HttpStatus.CREATED)
	public Course updateCourse(@RequestBody Course course)
	{
		courseService.save(course);

		return course;
	}

	@DeleteMapping("/{courseId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCourse(@PathVariable Long courseId)
	{
		courseService.deleteById(courseId);
	}
}
