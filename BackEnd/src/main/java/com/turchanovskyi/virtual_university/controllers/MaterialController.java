package com.turchanovskyi.virtual_university.controllers;

import com.turchanovskyi.virtual_university.interfaces.MaterialService;
import com.turchanovskyi.virtual_university.model.Material;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/materials")
public class MaterialController {

	private final MaterialService materialService;

	public MaterialController(MaterialService materialService) {
		this.materialService = materialService;
	}

	@GetMapping
	public Iterable<Material> main()
	{
		return materialService.findAll();
	}

	@GetMapping("/{materialId}")
	public Material getMaterial(@PathVariable Long materialId)
	{
		return materialService.findById(materialId);
	}

	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public Material addMaterial(@RequestBody Material material)
	{
		material.setMaterial_id(null);

		materialService.save(material);

		return material;
	}

	@PutMapping("/update")
	@ResponseStatus(HttpStatus.CREATED)
	public Material updateMaterial(@RequestBody Material material)
	{
		materialService.save(material);

		return material;
	}

	@DeleteMapping("/delete/{materialId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteMaterial(@PathVariable Long materialId)
	{
		materialService.deleteById(materialId);
	}
}
