package com.turchanovskyi.virtual_university.controllers;

import com.turchanovskyi.virtual_university.config.jwt.JwtProvider;
import com.turchanovskyi.virtual_university.interfaces.UserService;
import com.turchanovskyi.virtual_university.message.request.LoginForm;
import com.turchanovskyi.virtual_university.message.request.SignUpForm;
import com.turchanovskyi.virtual_university.message.response.JwtResponse;
import com.turchanovskyi.virtual_university.message.response.ResponseMessage;
import com.turchanovskyi.virtual_university.model.Role;
import com.turchanovskyi.virtual_university.model.RoleName;
import com.turchanovskyi.virtual_university.model.User;
import com.turchanovskyi.virtual_university.repository.RoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AuthenticationManager authenticationManager;

	private final UserService userService;

	private final RoleRepository roleRepository;

	private final PasswordEncoder passwordEncoder;

	private final JwtProvider jwtProvider;

	public AuthController(AuthenticationManager authenticationManager,
	                      UserService userService,
	                      RoleRepository roleRepository, PasswordEncoder passwordEncoder,
	                      JwtProvider jwtProvider) {
		this.authenticationManager = authenticationManager;
		this.userService = userService;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtProvider = jwtProvider;
	}

	@PostMapping
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginForm)
	{
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginForm.getLogin(), loginForm.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateJwtToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(),userDetails.getAuthorities()));
	}

	@PostMapping("/create")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> createUser(@Valid @RequestBody SignUpForm signUpForm)
	{
		User user = new User(
				signUpForm.getLogin(),
				passwordEncoder.encode(signUpForm.getPassword()),
				signUpForm.getName(),
				signUpForm.getSurname(),
				signUpForm.getCountry(),
				signUpForm.getEmail(),
				signUpForm.getCity());

		Set<String> strRoles = signUpForm.getRole_id();
		Set<Role> roles = new HashSet<>();

		strRoles.forEach(role -> {
			switch (role)
			{
				case "ROLE_ADMIN":
					Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
					roles.add(adminRole);
					break;
				case "ROLE_USER":
					Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
					roles.add(userRole);
					break;
				case "ROLE_PROFESSOR":
					Role professorRole = roleRepository.findByName(RoleName.ROLE_PROFESSOR)
							.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
					roles.add(professorRole);
					break;
			}
		});

		user.setRole_id(roles);
		userService.save(user);

		return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
	}
}
