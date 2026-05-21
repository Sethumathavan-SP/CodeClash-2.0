package com.Sethu.CodeClash.Services;

import com.Sethu.CodeClash.db.UserDAO;
import com.Sethu.CodeClash.models.User;
import com.Sethu.CodeClash.models.UserPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class userLoginService implements UserDetailsService {

    private final UserDAO userDAO;

    public userLoginService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return new UserPrincipal(user);
    }
}

