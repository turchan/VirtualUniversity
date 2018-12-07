package com.turchanovskyi.virtual_university.controllers;

import com.turchanovskyi.virtual_university.interfaces.MarkService;
import com.turchanovskyi.virtual_university.model.Mark;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/marks")
public class MarkController {

	private final MarkService markService;

	public MarkController(MarkService markService) {
		this.markService = markService;
	}

	@GetMapping
	public Iterable<Mark> main()
	{
		return markService.findAll();
	}

	@GetMapping("/{markId}")
	public Mark getMark(@PathVariable String markId)
	{
		return markService.findById(markId);
	}

	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public Mark addMark(@RequestBody Mark mark)
	{
		mark.setTitle_id(null);

		markService.save(mark);

		return mark;
	}

	@PutMapping("/update")
	@ResponseStatus(HttpStatus.CREATED)
	public Mark updateMark(@RequestBody Mark mark)
	{
		markService.save(mark);

		return mark;
	}

	@DeleteMapping("/delete/{markId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteMark(@PathVariable String markId)
	{
		markService.deleteById(markId);
	}
}
