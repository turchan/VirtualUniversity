package com.turchanovskyi.virtual_university.repository;

import com.turchanovskyi.virtual_university.model.Mark;
import org.springframework.data.repository.CrudRepository;

public interface MarkRepository<T extends Mark> extends CrudRepository<T, Long> {
}
