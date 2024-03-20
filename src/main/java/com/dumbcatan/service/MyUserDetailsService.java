package com.dumbcatan.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dumbcatan.dao.UserRepository;
import com.dumbcatan.entity.MyUserDetails;
import com.dumbcatan.entity.User;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// return new MyUserDetails(username);
		Optional<User> passenger = userRepository.findByUsername(username);
		
		passenger.orElseThrow(() -> new UsernameNotFoundException("user not found: " + username));
		
		return passenger.map(MyUserDetails::new).get();
	}

}
