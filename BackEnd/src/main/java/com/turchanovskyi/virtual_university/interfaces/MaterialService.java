package com.turchanovskyi.virtual_university.interfaces;

import com.turchanovskyi.virtual_university.model.Material;

import java.util.List;

public interface MaterialService {

	List<Material> findAll();
	Material findById(Long id);
	Material save(Material material);
	void delete(Material material);
}
