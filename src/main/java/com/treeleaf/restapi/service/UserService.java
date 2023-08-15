package com.treeleaf.restapi.service;

import com.treeleaf.restapi.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User save (User user);
    void delete(Long id);
    User update(Long id, User user);
    Boolean existsByUsernameAndPassword(String username, String password);
}
