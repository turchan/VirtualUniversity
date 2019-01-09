package com.turchanovskyi.virtual_university.repository;

import com.turchanovskyi.virtual_university.model.Material;
import org.springframework.data.repository.CrudRepository;

public interface MaterialRepository<T extends Material> extends CrudRepository<T, Long> {
}
