package com.spring.boot.security.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.security.entity.User;

@Repository
public interface UserDaoRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
}
