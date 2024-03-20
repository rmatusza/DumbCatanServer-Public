package com.dumbcatan.service;

import java.util.List;

import com.dumbcatan.entity.AuthenticationLog;

public interface AuthenticationLogService {
	
	public List<AuthenticationLog> findAll();
	
	public AuthenticationLog findById(int id);
	
	public void save(AuthenticationLog passenger);
	
	public void deleteById(int id);
}
