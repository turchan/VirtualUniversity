package com.turchanovskyi.virtual_university.interfaces;

import com.turchanovskyi.virtual_university.model.Material;

public interface MaterialService {

	Iterable<Material> findAll();
	Material findById(Long id);
	Material save(Material material);
	void deleteById(Long id);
}
