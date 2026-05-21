package com.Sethu.CodeClash.Services;

import com.Sethu.CodeClash.Security.JwtService;
import com.Sethu.CodeClash.db.UserDAO;
import com.Sethu.CodeClash.models.User;
import com.Sethu.CodeClash.models.UserPrincipal;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationService(UserDAO userDAO, PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public void registerUser(String email, String userName, String password) {
        if (userDAO.findByEmail(email) != null) {
            throw new RuntimeException("Email already registered");
        }
        if (userDAO.findByUserName(userName) != null) {
            throw new RuntimeException("Username already taken");
        }

        User user = new User();
        user.setEmail(email);
        user.setUsername(userName);
        user.setPassword(passwordEncoder.encode(password));
        user.setCreatedAt(System.currentTimeMillis());

        userDAO.save(user);
    }

    public String authenticateUser(String userName, String password) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userName, password));

        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        return jwtService.generateToken(principal);
    }

    public User findUserByUserName(String userName) {
        return userDAO.findByUserName(userName);
    }

    public User findUserById(String id) {
        return userDAO.findById(id);
    }
}
