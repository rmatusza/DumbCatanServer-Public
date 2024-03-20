package com.dumbcatan.controller;

import java.util.HashMap;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dumbcatan.entity.AuthenticationRequest;
import com.dumbcatan.entity.MyUserDetails;
import com.dumbcatan.entity.User;
import com.dumbcatan.service.AuthenticationLogService;
import com.dumbcatan.service.MyUserDetailsService;
import com.dumbcatan.service.UserServiceImpl;
import com.dumbcatan.util.JwtUtil;
import com.dumbcatan.util.ObtainDate;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
@RequestMapping("/api")
public class Authentication {
	
	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	@Autowired
	AuthenticationLogService authenticationLogService;
	@Autowired
	ObtainDate obtainDate;
	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private MyUserDetailsService userDetailsService;
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		User user = null;

		try {
			authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		} 
		catch(BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
		
		final String jwt = jwtTokenUtil.generateToken((MyUserDetails) userDetails);
		final MyUserDetails myUserDetails = (MyUserDetails) userDetails;		
		final HashMap<String, Object> res = new HashMap<>();
		
		res.put("jwt", jwt);
		res.put("role", myUserDetails.getRole());
		res.put("id", myUserDetails.getId());
		res.put("avatar_url", myUserDetails.getAvatarURL());
		
		String date = obtainDate.getTodayDate(obtainDate.getDateFormat(), obtainDate.getTimeZone());
		String time = obtainDate.getCurrentTime(obtainDate.getTimeFormat(), obtainDate.getTimeZone());
		String timestamp = date + " - " + time;

		return ResponseEntity.ok(res);
	}	
	
	@GetMapping("/authenticate")
	public ResponseEntity<?> authenticateToken(@RequestHeader (name="token") String token) throws Exception {
		String username;
		
		try {
			jwtTokenUtil.extractAllClaims(token);
			username = jwtTokenUtil.extractUsername(token);
		}catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
		User user = userService.findByUsername(username);
		HashMap<String, Object> userData = new HashMap<>();
		userData.put("username", username);
		userData.put("id", user.getUserId());
		userData.put("avatar_url", user.getAvatarURL());
		userData.put("role", user.getRole());
		
		return ResponseEntity.ok(userData);
	}	
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody User newUser) {
		final HashMap<String, Object> res = new HashMap<>();
		String password = newUser.getPassword();
		
		String hashedPassword = bCryptPasswordEncoder.encode(password);
		newUser.setPassword(hashedPassword);
		newUser.setAvatarURL("./av_1.png");
		newUser.setLoggedIn(1);
		try{
			User savedUser = userService.save(newUser);

			final MyUserDetails userDetails = new MyUserDetails(newUser);

			final String jwt = jwtTokenUtil.generateToken(userDetails);
			final MyUserDetails user = (MyUserDetails) userDetails;


			res.put("jwt", jwt);
			res.put("role", user.getRole());
			res.put("id", savedUser.getUserId());
			res.put("avatar_url", "./av_1.png");
			res.put("status", 200);
			res.put("message", "success");

			return ResponseEntity.ok(res);
		}
		catch(Exception e){
			res.put("jwt", null);
			res.put("role", null);
			res.put("id", null);
			res.put("avatar_url", null);
			res.put("status", 500);
			res.put("message", e.getMessage());
			return ResponseEntity.ok(res);
		}
	}
}