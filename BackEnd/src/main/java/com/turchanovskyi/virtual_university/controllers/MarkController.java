package com.turchanovskyi.virtual_university.controllers;

import com.turchanovskyi.virtual_university.interfaces.CourseService;
import com.turchanovskyi.virtual_university.interfaces.MarkService;
import com.turchanovskyi.virtual_university.interfaces.UserService;
import com.turchanovskyi.virtual_university.model.Course;
import com.turchanovskyi.virtual_university.model.Mark;
import com.turchanovskyi.virtual_university.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/marks")
public class MarkController {

	private final MarkService<Mark> markService;
	private final UserService<User> userService;
	private final CourseService<Course> courseService;

	public MarkController(MarkService<Mark> markService, UserService<User> userService, CourseService<Course> courseService) {
		this.markService = markService;
		this.userService = userService;
		this.courseService = courseService;
	}

	@GetMapping
	public List<Mark> main()
	{
		return markService.findAll();
	}

	@GetMapping("/{markId}")
	public Mark getMark(@PathVariable Long markId)
	{
		return markService.findById(markId);
	}

	@PostMapping("/add/{userId}/{courseId}")
	@PreAuthorize("hasRole('ROLE_PROFESSOR')")
	@ResponseStatus(HttpStatus.CREATED)
	public Mark addMark(@RequestBody Mark mark, @PathVariable Long userId, @PathVariable Long courseId)
	{
		User user = userService.findById(userId);
		Course course = courseService.findById(courseId);

		mark.setMark_id(null);
		mark.setUser(user);
		mark.setCourse(course);
		user.getMarkList().add(mark);

		markService.save(mark);

		return mark;
	}

	@PutMapping("/update")
	@PreAuthorize("hasRole('ROLE_PROFESSOR')")
	@ResponseStatus(HttpStatus.CREATED)
	public Mark updateMark(@RequestBody Mark mark)
	{
		markService.save(mark);

		return mark;
	}

	@DeleteMapping("/delete/{markId}")
	@PreAuthorize("hasRole('ROLE_PROFESSOR')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteMark(@PathVariable Long markId)
	{
		markService.deleteById(markId);
	}
}
