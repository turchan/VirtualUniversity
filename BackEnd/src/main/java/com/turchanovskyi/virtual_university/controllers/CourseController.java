package com.turchanovskyi.virtual_university.controllers;

import com.turchanovskyi.virtual_university.interfaces.CourseService;
import com.turchanovskyi.virtual_university.interfaces.UserService;
import com.turchanovskyi.virtual_university.model.Course;
import com.turchanovskyi.virtual_university.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/course")
public class CourseController {

	private final CourseService<Course> courseService;
	private final UserService userService;

	public CourseController(CourseService<Course> courseService, UserService userService) {
		this.courseService = courseService;
		this.userService = userService;
	}

	@GetMapping
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_PROFESSOR') or hasRole('ROLE_ADMIN')")
	public Iterable<Course> main()
	{
		return courseService.findAll();
	}

	@GetMapping("/{courseId}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_PROFESSOR') or hasRole('ROLE_ADMIN')")
	public Course getCourse(@PathVariable Long courseId)
	{
		return courseService.findById(courseId);
	}

	@GetMapping("/search/{title}")
	public List<Course> searchCourse(@PathVariable String title)
	{
		List<Course> courseList = new ArrayList<>();

		List<Course> findCourse = courseService.findByTitle(title);
		Iterator<Course> iter = findCourse.iterator();

		while (iter.hasNext())
		{
			courseList.add(iter.next());
		}

		return courseList;

		//return courseService.findByTitle(title);
	}

	@PostMapping("/create")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PROFESSOR')")
	@ResponseStatus(HttpStatus.CREATED)
	public Course createCourse(@RequestBody Course course)
	{
		course.setCourse_id(null);

		courseService.save(course);

		return course;
	}

	@PutMapping("/update")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PROFESSOR')")
	@ResponseStatus(HttpStatus.CREATED)
	public Course updateCourse(@RequestBody Course course)
	{
		courseService.save(course);

		return course;
	}

	@GetMapping("/{courseId}/addUser/{userId}")
	@PreAuthorize("hasRole('ROLE_PROFESSOR') or hasRole('ROLE_USER')")
	public void addUser(@PathVariable Long courseId, @PathVariable Long userId)
	{
		Course course = courseService.findById(courseId);
		User user = userService.findById(userId);

		course.getUserList().add(user);
		user.getCoursesList().add(course);

		courseService.save(course);
	}

	@GetMapping("/{courseId}/{userId}")
	@PreAuthorize("hasRole('ROLE_PROFESSOR')")
	public void deleteUser(@PathVariable Long courseId, @PathVariable Long userId)
	{
		Course course = courseService.findById(courseId);
		User user = userService.findById(userId);

		course.getUserList().remove(user);
		user.getCoursesList().remove(course);

		courseService.save(course);
	}

	@DeleteMapping("/{courseId}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PROFESSOR')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCourse(@PathVariable Long courseId)
	{
		courseService.deleteById(courseId);
	}
}
