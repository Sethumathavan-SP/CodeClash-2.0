package com.Sethu.CodeClash.Controller;

import com.Sethu.CodeClash.Services.AuthenticationService;
import com.Sethu.CodeClash.models.LoginResponse;
import com.Sethu.CodeClash.models.SignUpResponse;
import com.Sethu.CodeClash.models.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> signup(@RequestBody UserDTO userDTO) {
        try {
            authenticationService.registerUser(userDTO.getEmail(), userDTO.getUserName(), userDTO.getPassword());
            String token = authenticationService.authenticateUser(userDTO.getUserName(), userDTO.getPassword());

            SignUpResponse response = new SignUpResponse();
            response.setToken(token);
            response.setUserName(userDTO.getUserName());
            response.setEmail(userDTO.getEmail());
            response.setMessage("User registered successfully");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UserDTO userDTO) {
        try {
            String token = authenticationService.authenticateUser(userDTO.getUserName(), userDTO.getPassword());
            var user = authenticationService.findUserByUserName(userDTO.getUserName());

            LoginResponse response = new LoginResponse();
            response.setToken(token);
            response.setUserName(user.getUsername());
            response.setEmail(user.getEmail());
            response.setUserId(user.getId());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}

