package com.dumbcatan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dumbcatan.dao.AuthenticationLogRepository;
import com.dumbcatan.entity.AuthenticationLog;

@Service
public class AuthenticationLogServiceImpl implements AuthenticationLogService {
	
	@Autowired
	AuthenticationLogRepository repo;

	@Override
	public List<AuthenticationLog> findAll() {
		return repo.findAll();
	}

	@Override
	public AuthenticationLog findById(int id) {
		
		Optional<AuthenticationLog> result = repo.findById(id);
		AuthenticationLog log = null;
		
		if (result.isPresent()) {
			log = result.get();
		}else {
			throw new RuntimeException("Did not find authentication log with username - " + id);
		}
		
		return log;
	}

	@Override
	public void save(AuthenticationLog log) {
		repo.save(log);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub

	}

}
