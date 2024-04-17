package com.visiontech.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.visiontech.DTOS.AuthenticationResponse;
import com.visiontech.DTOS.SignInDto;
import com.visiontech.DTOS.SignupDto;
import com.visiontech.model.Role;
import com.visiontech.model.User;
import com.visiontech.repos.UserRepo;

@Service
public class LoginService {
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public AuthenticationResponse signInUser(SignInDto signIn) throws Exception {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(signIn.getEmail(), signIn.getPassword()));
		var user = userRepo.findByEmail(signIn.getEmail())
				.orElseThrow(() -> new Exception("Invalid email or password"));
		var jwtToken=jwtService.generateToken(user);
		return AuthenticationResponse.builder().token(jwtToken).build();
	}

	public User createUser(SignupDto signup) throws Exception {
		User user = new User();
		user.setEmail(signup.getEmail());
		user.setPassword(passwordEncoder.encode(signup.getPassword()));
		user.setFirstName(signup.getFirstName());
		user.setLastName(signup.getLastName());
		user.setRole(Role.USER);
		return userRepo.save(user);
	}

}
