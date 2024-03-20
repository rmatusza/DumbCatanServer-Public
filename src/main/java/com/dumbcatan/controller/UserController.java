package com.dumbcatan.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dumbcatan.entity.MyUserDetails;
import com.dumbcatan.entity.User;
import com.dumbcatan.entity.http.ResponseObj;
import com.dumbcatan.service.MyUserDetailsService;
import com.dumbcatan.service.UserServiceImpl;
import com.dumbcatan.util.JwtUtil;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api")
public class UserController {
	
	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@PutMapping("/users/{userId}/edit-username")
	public ResponseEntity<?> editUsername(@RequestBody User newUser, @PathVariable("userId") int userId) {
		
		User user = userService.findById(userId);
		user.setUsername(newUser.getUsername());
		
		userService.save(user);
		
		MyUserDetails userDetails = new MyUserDetails(user);
		
		String jwt = jwtTokenUtil.generateToken(userDetails);
		
		HashMap<String, String> response = new HashMap<>();
		response.put("jwt", jwt);
		response.put("username", newUser.getUsername());
		
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/users/{userId}/edit-password")
	public ResponseEntity<?> editPassword(@RequestBody User newUser, @PathVariable("userId") int userId) {
		
		User user = userService.findById(userId);
		String hashedPassword = bCryptPasswordEncoder.encode(newUser.getPassword());
		user.setPassword(hashedPassword);
		
		userService.save(user);
		
		MyUserDetails userDetails = new MyUserDetails(user);
		
		String jwt = jwtTokenUtil.generateToken(userDetails);
		
		HashMap<String, String> response = new HashMap<>();
		response.put("jwt", jwt);
		
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/users/{userId}/edit-username-and-password")
	public ResponseEntity<?> editAll(@RequestBody User newUser, @PathVariable("userId") int userId) {
		
		User user = userService.findById(userId);
		String hashedPassword = bCryptPasswordEncoder.encode(newUser.getPassword());
		user.setPassword(hashedPassword);
		user.setUsername(newUser.getUsername());
		
		userService.save(user);
		
		MyUserDetails userDetails = new MyUserDetails(user);
		
		String jwt = jwtTokenUtil.generateToken(userDetails);
		
		HashMap<String, String> response = new HashMap<>();
		response.put("jwt", jwt);
		response.put("username", newUser.getUsername());
		
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/users/{userId}/edit-avatar")
	public ResponseEntity<?> editAvatar(@RequestBody User newUser, @PathVariable("userId") int userId) {
		
		User user = userService.findById(userId);
		user.setAvatarURL(newUser.getAvatarURL());
		
		
		userService.save(user);
		
		
		HashMap<String, String> response = new HashMap<>();
		response.put("avatar_url", newUser.getAvatarURL());
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/users/{userId}/activeGameCount")
	public ResponseObj getActiveGameCount(@PathVariable int userId) {
		ResponseObj res = new ResponseObj();
		
		try {
			User user = userService.findById(userId);
			int activeGameCount = user.getActiveGames();
			if(activeGameCount == 3) {
				res.setStatus(403);
				res.setMessage("You can only have 3 active games at a time");
				return res;
			}
			else {
				res.setStatus(200);
				res.setMessage("Below game limit");
				return res;
			}
		}
		catch(Exception e) {
			res.setStatus(500);
			res.setMessage("An error occured when trying to fetch this user's data");
			return res;
		}
	}
	
	@PutMapping("/users/{userId}/addWin")
	public ResponseObj addWin(@PathVariable int userId) {
		ResponseObj res = new ResponseObj();
		
		try {
			User user = userService.findById(userId);
			int winCount = user.getWins();
			winCount += 1;
			user.setWins(winCount);
			userService.save(user);
			res.setStatus(200);
			res.setMessage("Win count updated");
			return res;
		}
		catch(Exception e) {
			res.setStatus(500);
			res.setMessage("An error occurred when trying to update win count.");
			return res;
		}
	}
	
	@PutMapping("/users/{userId}/addLoss")
	public ResponseObj addLoss(@PathVariable int userId) {
		ResponseObj res = new ResponseObj();
		
		try {
			User user = userService.findById(userId);
			int lossCount = user.getLosses();
			lossCount += 1;
			user.setLosses(lossCount);
			userService.save(user);
			res.setStatus(200);
			res.setMessage("Loss count updated");
			return res;
		}
		catch(Exception e) {
			res.setStatus(500);
			res.setMessage("An error occurred when trying to update loss count.");
			return res;
		}
	}
}
