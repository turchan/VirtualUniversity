package com.turchanovskyi.virtual_university.interfaces;

import com.turchanovskyi.virtual_university.model.Mark;

import java.util.List;

public interface MarkService<T extends Mark> {

	List<T> findAll();
	T findById(Long id);
	T save(T mark);
	void deleteById(Long id);
}
