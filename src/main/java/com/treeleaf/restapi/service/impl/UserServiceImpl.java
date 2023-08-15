package com.treeleaf.restapi.service.impl;

import com.treeleaf.restapi.entity.User;
import com.treeleaf.restapi.exception.UserNotFoundException;
import com.treeleaf.restapi.repository.UserRepo;
import com.treeleaf.restapi.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    @Override
    public List<User> getAllUsers() {
        log.info("All User Return");
        return userRepo.findAll();
    }

    @Override
    public User getUserById(Long id) {
        User checkUser = userRepo.getById(id);
        if (checkUser!=null) {
            return checkUser;
        }else {
            log.error("User Search Error.");
            throw new UserNotFoundException("User Not Found.");
        }
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("User save successfully");
        return userRepo.save(user);
    }

    @Override
    public void delete(Long id) {
        Optional<User> user = userRepo.findById(id);
        if (user.isEmpty()) {
            log.error("User Search Error for id "+ id);
            throw new UserNotFoundException("User Not Found.");
        }else {
            log.info("blog Post delete successful.");
            userRepo.deleteById(id);
        }
    }

    @Override
    public User update(Long id, User user) {
        Optional<User> result = userRepo.findById(id);
        if (result.isPresent()) {
            User userPersistent = result.get();
            userPersistent.setFirstName(user.getFirstName());
            userPersistent.setLastName(user.getLastName());
            userPersistent.setUsername(user.getUsername());
            userPersistent.setPassword(user.getPassword());
            userRepo.save(userPersistent);
            log.info("user update for id " + id);
            return userPersistent;
        }else {
            log.info("user search error for id " + id);
            throw new UserNotFoundException("User Not Found.");
        }
    }

    @Override
    public Boolean existsByUsernameAndPassword(String userName, String password) {
        Optional<User> existUser = userRepo.existsByUsername(userName);
        Optional<User> checkUser = userRepo.checkUser(userName, password);
        if (existUser.isEmpty()) {
            log.info("User not Exist");
            return false;
        }
        if (existUser.isPresent()) {
            if (checkUser.isPresent()){
                log.info("User Exist");
                return true;
            }else {
                log.info("password not match");
                return false;
            }
        }
        return true;
    }
}
