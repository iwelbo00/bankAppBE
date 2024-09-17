package com.ivans.bankApp.controller;

import com.ivans.bankApp.entity.userDetails;
import com.ivans.bankApp.repository.usersRepository;
import com.ivans.bankApp.services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("http://localhost:8081/")

public class usersController {
    private com.ivans.bankApp.services.userService userService;

    @Autowired
    private usersRepository usersRepository;
    @Autowired
    public void userController(userService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody userDetails request){
        userDetails user = usersRepository.findByUsernameAndPassword(request.getUsername(),
                this.userService.hashString(request.getPassword()));
        if(user != null){
            return ResponseEntity.ok("Authorization Successful");
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody userDetails request) {
        userDetails user = usersRepository.findByUsername(request.getUsername());
        if(user == null){
            return ResponseEntity.ok(userService.createUser(request.getUsername(), request.getPassword()));
        }else{
            return ResponseEntity.badRequest().body("Username already taken");
        }
    }
}
