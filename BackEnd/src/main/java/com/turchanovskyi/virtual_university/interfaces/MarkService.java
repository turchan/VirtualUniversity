package com.turchanovskyi.virtual_university.interfaces;

import com.turchanovskyi.virtual_university.model.Mark;

import java.util.List;

public interface MarkService {

	List<Mark> findAll();
	Mark findById(String id);
	Mark save(Mark mark);
	void delete(Mark mark);
}
