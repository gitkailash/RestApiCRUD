package com.treeleaf.restapi.controller;

import com.treeleaf.restapi.entity.User;
import com.treeleaf.restapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@AllArgsConstructor
@RequestMapping("api/auth")
public class AuthController {
    private final UserService userService;
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> credentials){
        String username = credentials.get("username");
        String password = credentials.get("password");

         boolean checkUser = userService.existsByUsernameAndPassword(username,password);
         if (checkUser) {
             System.out.println("Login success...");
             return ResponseEntity.status(HttpStatus.OK).body("Login successfully");
         }else {
             return ResponseEntity.status(HttpStatus.OK).body("Please Check Credential or Register");
         }
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user){
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }
}
