package com.turchanovskyi.virtual_university.interfaces;

import com.turchanovskyi.virtual_university.model.Course;

import java.util.List;

public interface CourseService {

	List<Course> findAll();
	Course findById(Long id);
	Course save(Course course);
	void delete(Course course);
}
