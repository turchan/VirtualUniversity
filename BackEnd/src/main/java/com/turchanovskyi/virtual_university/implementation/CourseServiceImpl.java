package com.turchanovskyi.virtual_university.implementation;

import com.turchanovskyi.virtual_university.interfaces.CourseService;
import com.turchanovskyi.virtual_university.model.Course;
import com.turchanovskyi.virtual_university.repository.CourseRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@Service("courseService")
public class CourseServiceImpl implements CourseService {

	private final CourseRepository<Course> courseRepository;

	public CourseServiceImpl(CourseRepository<Course> courseRepository) {
		this.courseRepository = courseRepository;
	}

	@Transactional(readOnly = true)
	@Override
	public Iterable<Course> findAll() {
		return courseRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Course findById(Long id) {
		return courseRepository.findById(id).get();
	}

	@Transactional(readOnly = true)
	@Override
	public List<Course> findByTitle(String title) {
		return courseRepository.findByTitle(title);
	}

	@Transactional
	@Override
	public Course save(Course course) {
		return courseRepository.save(course);
	}

	@Transactional
	@Override
	public void deleteById(Long id) {
		courseRepository.deleteById(id);
	}
}
