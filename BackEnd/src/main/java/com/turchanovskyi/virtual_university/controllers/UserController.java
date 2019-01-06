package com.turchanovskyi.virtual_university.controllers;

import com.turchanovskyi.virtual_university.interfaces.UserService;
import com.turchanovskyi.virtual_university.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController
{
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_PROFESSOR') or hasRole('ROLE_ADMIN')")
	public Iterable<User> main()
	{
		return userService.findAll();
	}

	@GetMapping("/{userId}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_PROFESSOR') or hasRole('ROLE_ADMIN')")
	public User getUser(@PathVariable Long userId)
	{
		return userService.findById(userId);
	}

	@GetMapping("/search/{surname}")
	public List<User> searchUser(@PathVariable String surname)
	{
		List<User> userList = new ArrayList<>();

		List<User> findUser = userService.findBySurname(surname);
		Iterator<User> iter = findUser.iterator();

		while (iter.hasNext())
		{
			userList.add(iter.next());
		}

		return userList;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_PROFESSOR') or hasRole('ROLE_ADMIN')")
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
