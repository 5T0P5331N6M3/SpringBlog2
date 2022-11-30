package com.example.springblog3;

import com.example.springblog3.models.User;
import com.example.springblog3.models.UserWithRoles;
import com.example.springblog3.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsLoader implements UserDetailsService {

    private final UserRepository userDao;

    public UserDetailsLoader(UserRepository userDao) {
        this.userDao = userDao;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("No user found for: " + username);
        } else {

        }
        // instantiate a new User through the super constructor.
        return new UserWithRoles(user);
    }
}
