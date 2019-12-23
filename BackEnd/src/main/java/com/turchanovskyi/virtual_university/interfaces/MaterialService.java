package com.turchanovskyi.virtual_university.interfaces;

import com.turchanovskyi.virtual_university.model.Material;

import java.util.List;

public interface MaterialService<T extends Material> {

	List<T> findAll();
	T findById(Long id);
	T save(T material);
	void deleteById(Long id);
}
