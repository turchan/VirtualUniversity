package com.turchanovskyi.virtual_university.repository;

import com.turchanovskyi.virtual_university.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
	List<Course> findByTitle(String title);
}
