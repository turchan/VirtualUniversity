package com.turchanovskyi.virtual_university.controllers;

import com.turchanovskyi.virtual_university.implementation.StorageService;
import com.turchanovskyi.virtual_university.interfaces.MaterialService;
import com.turchanovskyi.virtual_university.model.Material;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 3600)
@Controller
@RequestMapping
public class UploadController {

	private final StorageService storageService;
	private final MaterialService materialService;

	public UploadController(StorageService storageService, MaterialService materialService) {
		this.storageService = storageService;
		this.materialService = materialService;
	}

	private List<String> files = new ArrayList<>();
	private final Path rootLocation = Paths.get("upload-dir");

	@GetMapping("/getallfiles")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PROFESSOR')")
	public ResponseEntity<List<String>> getListFiles(Model model)
	{
		List<String> fileNames = files
				.stream().map(fileName -> MvcUriComponentsBuilder
				.fromMethodName(UploadController.class, "getFile", fileName).build().toString())
				.collect(Collectors.toList());

		return ResponseEntity.ok().body(fileNames);
	}

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable String filename)
	{
		Resource file = storageService.loadFile(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
}
