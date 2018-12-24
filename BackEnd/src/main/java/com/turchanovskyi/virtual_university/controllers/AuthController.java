package com.turchanovskyi.virtual_university.controllers;

import com.turchanovskyi.virtual_university.config.jwt.JwtProvider;
import com.turchanovskyi.virtual_university.interfaces.UserService;
import com.turchanovskyi.virtual_university.message.request.LoginForm;
import com.turchanovskyi.virtual_university.message.response.JwtResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/auth")
public class    AuthController {

	private final AuthenticationManager authenticationManager;

	private UserService userService;

	private PasswordEncoder passwordEncoder;

	private JwtProvider jwtProvider;

	public AuthController(AuthenticationManager authenticationManager,
	                      UserService userService,
	                      PasswordEncoder passwordEncoder,
	                      JwtProvider jwtProvider) {
		this.authenticationManager = authenticationManager;
		this.userService = userService;
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
}
