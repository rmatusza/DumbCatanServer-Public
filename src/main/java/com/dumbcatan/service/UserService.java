package com.dumbcatan.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dumbcatan.entity.User;


public interface UserService {
	
	public List<User> findAll();
	
	public User findById(int id);
	
	public User save(User user);
	
	public void deleteById(int id);
	
	public User findByUsername(String username);
}
