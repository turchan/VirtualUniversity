package com.turchanovskyi.virtual_university.interfaces;

import com.turchanovskyi.virtual_university.model.Course;

import java.util.List;

public interface CourseService<T extends Course> {

	Iterable<T> findAll();
	T findById(Long id);
	List<T> findByTitle(String title);
	T save(T course);
	void deleteById(Long id);
}
