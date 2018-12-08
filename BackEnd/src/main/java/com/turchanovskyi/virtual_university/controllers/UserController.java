package com.turchanovskyi.virtual_university.controllers;

import com.turchanovskyi.virtual_university.interfaces.UserService;
import com.turchanovskyi.virtual_university.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/user")
public class UserController
{
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public Iterable<User> main()
	{
		return userService.findAll();
	}

	@GetMapping("/{userId}")
	public User getUser(@PathVariable Long userId)
	{
		return userService.findById(userId);
	}


	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public User createUser(@RequestBody User user)
	{
		user.setUser_id(null);

		userService.save(user);

		return user;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping("/update")
	public User updateUser(@RequestBody User user)
	{
		userService.save(user);

		return user;
	}

	@DeleteMapping("/delete/{userId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable Long userId)
	{
		userService.deleteById(userId);
	}

}
