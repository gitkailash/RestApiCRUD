package com.treeleaf.restapi.repository;

import com.treeleaf.restapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.username = :username")
    Optional<User> existsByUsername(@Param("username") String username);

    @Query("SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
    Optional<User> checkUser(@Param("username") String username, @Param("password") String password);

    @Query("SELECT u FROM User u WHERE u.id = :id")
    User getById(@Param("id") Long id);

    Optional<User> findByUsername(String username);

}