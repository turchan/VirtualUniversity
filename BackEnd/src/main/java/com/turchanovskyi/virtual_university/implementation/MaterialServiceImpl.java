package com.turchanovskyi.virtual_university.implementation;

import com.turchanovskyi.virtual_university.interfaces.MaterialService;
import com.turchanovskyi.virtual_university.model.Material;
import com.turchanovskyi.virtual_university.repository.MaterialRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@Service("materialService")
public class MaterialServiceImpl implements MaterialService {

	private final MaterialRepository<Material> materialRepository;

	public MaterialServiceImpl(MaterialRepository<Material> materialRepository) {
		this.materialRepository = materialRepository;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Material> findAll() {
		return materialRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Material findById(Long id) {
		return materialRepository.findById(id).get();
	}

	@Transactional
	@Override
	public Material save(Material material) {
		return materialRepository.save(material);
	}

	@Transactional
	@Override
	public void deleteById(Long id) {
		materialRepository.deleteById(id);
	}
}
