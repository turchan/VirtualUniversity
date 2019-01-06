package com.turchanovskyi.virtual_university.repository;

import com.turchanovskyi.virtual_university.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
