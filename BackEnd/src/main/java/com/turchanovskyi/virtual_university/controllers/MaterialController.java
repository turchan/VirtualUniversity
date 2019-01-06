package com.turchanovskyi.virtual_university.controllers;

import com.turchanovskyi.virtual_university.implementation.StorageService;
import com.turchanovskyi.virtual_university.interfaces.CourseService;
import com.turchanovskyi.virtual_university.interfaces.MaterialService;
import com.turchanovskyi.virtual_university.model.Course;
import com.turchanovskyi.virtual_university.model.Material;
import com.turchanovskyi.virtual_university.repository.FileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/materials")
public class MaterialController {

	private final MaterialService materialService;
	private final CourseService courseService;
	private final StorageService storageService;
	private final FileRepository fileRepository;

	public MaterialController(MaterialService materialService, CourseService courseService, StorageService storageService, FileRepository fileRepository) {
		this.materialService = materialService;
		this.courseService = courseService;
		this.storageService = storageService;
		this.fileRepository = fileRepository;
	}

	private Set<String> files = new HashSet<>();
	private final Path rootLocation = Paths.get("upload-dir");

	@GetMapping
	public Iterable<Material> main()
	{
		return materialService.findAll();
	}

	@GetMapping("/{materialId}")
	public Material material(@PathVariable Long materialId)
	{
		return materialService.findById(materialId);
	}

	@PostMapping("/add/{courseId}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PROFESSOR')")
	@ResponseStatus(HttpStatus.CREATED)
	public Material addMaterial(@RequestBody Material material, @PathVariable Long courseId)
	{
		try
		{
			material.setMaterial_id(null);
			Course course = courseService.findById(courseId);
			material.setCourse(course);
			course.getMaterialList().add(material);

			List<String> fileNames = files
							.stream().map(fileName -> MvcUriComponentsBuilder
							.fromMethodName(UploadController.class, "getFile", fileName).build().toString())
							.collect(Collectors.toList());

			//material.setFileSet(fileNames);
			materialService.save(material);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return material;
	}

	@PostMapping("/post")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PROFESSOR')")
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file)
	{
		String message = "";
		try
		{
			storageService.store(file);

			files.add(file.getOriginalFilename());

			/*files.setId(null);
			files.setFilename(file.getOriginalFilename());

			Path path = rootLocation.resolve(file.getOriginalFilename());
			URI uri = path.toUri();
			files.setUrl(uri);

			fileRepository.save(files);*/

			message = "You successfully uploaded " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		}
		catch (Exception e)
		{
			message = "Fail to upload " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}

	@PutMapping("/update")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PROFESSOR')")
	@ResponseStatus(HttpStatus.CREATED)
	public Material updateMaterial(@RequestBody Material material)
	{
		materialService.save(material);

		return material;
	}

	@DeleteMapping("/delete/{materialId}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PROFESSOR')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteMaterial(@PathVariable Long materialId)
	{
		materialService.deleteById(materialId);
	}
}
