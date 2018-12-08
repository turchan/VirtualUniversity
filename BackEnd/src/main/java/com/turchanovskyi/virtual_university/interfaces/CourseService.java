package com.turchanovskyi.virtual_university.interfaces;

import com.turchanovskyi.virtual_university.model.Course;

public interface CourseService {

	Iterable<Course> findAll();
	Course findById(Long id);
	Course save(Course course);
	void deleteById(Long id);
}
