package com.turchanovskyi.virtual_university.repository;

import com.turchanovskyi.virtual_university.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository<T extends Course> extends JpaRepository<T, Long> {
	List<T> findByTitle(String title);
}
