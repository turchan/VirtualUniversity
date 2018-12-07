package com.turchanovskyi.virtual_university.interfaces;

import com.turchanovskyi.virtual_university.model.Mark;

public interface MarkService {

	Iterable<Mark> findAll();
	Mark findById(String id);
	Mark save(Mark mark);
	void deleteById(String id);
}
