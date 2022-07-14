package io.shlink.userservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import io.shlink.userservice.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}