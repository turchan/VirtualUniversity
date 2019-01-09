package com.turchanovskyi.virtual_university.interfaces;

import com.turchanovskyi.virtual_university.model.Material;

public interface MaterialService<T extends Material> {

	Iterable<T> findAll();
	T findById(Long id);
	T save(T material);
	void deleteById(Long id);
}
