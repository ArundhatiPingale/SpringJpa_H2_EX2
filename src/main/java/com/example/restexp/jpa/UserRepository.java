package com.example.restexp.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restexp.user.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
