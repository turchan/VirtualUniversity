package com.turchanovskyi.virtual_university.repository;

import com.turchanovskyi.virtual_university.model.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Long> { }
