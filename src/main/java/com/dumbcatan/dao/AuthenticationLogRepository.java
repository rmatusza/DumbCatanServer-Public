package com.dumbcatan.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dumbcatan.entity.AuthenticationLog;

public interface AuthenticationLogRepository extends JpaRepository<AuthenticationLog, Integer> {

	Optional<AuthenticationLog> findById(int id);
}
