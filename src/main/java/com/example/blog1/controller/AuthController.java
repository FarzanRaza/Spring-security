package com.example.blog1.controller;

import com.example.blog1.entity.User;
import com.example.blog1.payload.LoginDto;
import com.example.blog1.payload.SignUpDto;
import com.example.blog1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("/signup")
    public ResponseEntity<?> saveDetail(@RequestBody SignUpDto signUpDto){
        //email is registerd
        Boolean b = userRepository.existsByEmail(signUpDto.getEmail());
        if(b){
            return  new ResponseEntity<>("Email is already exist", HttpStatus.BAD_REQUEST);
        }
        Boolean b1 = userRepository.existsByUsername(signUpDto.getUsername());
        if(b1){
            return  new ResponseEntity<>("Username is already exist", HttpStatus.BAD_REQUEST);
        }
        User user= new User();
        user.setName(signUpDto.getName());
        user.setEmail(signUpDto.getEmail());
        user.setUsername(signUpDto.getUsername());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        User save = userRepository.save(user);
        return  new ResponseEntity<>("User is registered", HttpStatus.CREATED);

    }
    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto)
    {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(),loginDto.getPassword()) );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }

}
