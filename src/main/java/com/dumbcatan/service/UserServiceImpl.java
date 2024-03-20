package com.dumbcatan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dumbcatan.dao.UserRepository;
import com.dumbcatan.entity.User;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepo;
	
	public UserServiceImpl(UserRepository passengerRepo) {
		this.userRepo = passengerRepo;
	}
	
	@Override
	public User findByUsername(String username){
		Optional<User> result = userRepo.findByUsername(username);
		User user = null;
		
		if (result.isPresent()) {
			user = result.get();
		}else {
			throw new RuntimeException("Did not find user with username: " + username);
		}
		
		return user;
	}

	@Override
	public List<User> findAll() {
		return userRepo.findAll();
	}

	@Override
	public User findById(int id) {
		
		Optional<User> result = userRepo.findById(id);
		
		User user = null;
		
		if (result.isPresent()) {
			user = result.get();
		}else {
			throw new RuntimeException("Did not find user with id: " + id);
		}
		
		return user;
	}

	@Override
	public User save(User passenger) {
		return userRepo.save(passenger);
	}

	@Override
	public void deleteById(int id) {
		userRepo.deleteById(id);
	}

}
