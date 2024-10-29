package com.ivans.bankApp.controller;

import com.ivans.bankApp.entity.emailDetails;
import com.ivans.bankApp.entity.userDetails;
import com.ivans.bankApp.repository.usersRepository;
import com.ivans.bankApp.services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("http://localhost:5173")
public class usersController {
    private com.ivans.bankApp.services.userService userService;

    @Autowired
    private usersRepository usersRepository;
    @Autowired
    public void userController(userService userService) {
        this.userService = userService;
    }
    @Autowired
    private JavaMailSender mailSender;

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

    @PostMapping("/sendEmail")
    public void sendEmail(@RequestBody emailDetails request){
        sendEmail(request.getSendTo(), request.getSubject(), request.getBody());
    }


    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        for(int i = 0; i < 100; i++) {
            mailSender.send(message);
            message.setText(body+i);
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
