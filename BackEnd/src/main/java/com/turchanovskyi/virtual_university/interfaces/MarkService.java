package com.turchanovskyi.virtual_university.interfaces;

import com.turchanovskyi.virtual_university.model.Mark;

public interface MarkService<T extends Mark> {

	Iterable<T> findAll();
	T findById(Long id);
	T save(T mark);
	void deleteById(Long id);
}
