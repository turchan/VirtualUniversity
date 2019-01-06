package com.turchanovskyi.virtual_university.interfaces;

import com.turchanovskyi.virtual_university.model.Course;

import java.util.List;

public interface CourseService {

	Iterable<Course> findAll();
	Course findById(Long id);
	List<Course> findByTitle(String title);
	Course save(Course course);
	void deleteById(Long id);
}
