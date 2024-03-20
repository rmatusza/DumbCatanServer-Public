package com.dumbcatan.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dumbcatan.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUsername(String username);
	Optional<User> findById(int id);
}
