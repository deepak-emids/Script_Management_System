package com.emids.sms.service;


import com.emids.sms.model.Writer;
import com.emids.sms.repository.WriterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private WriterRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Writer user = repository.findByName(username);

        Writer w = new Writer();
        w.setName(user.getName());
        w.setPassword(user.getPassword());
        w.setRole(user.getRole());

        CustomUserDetails userDetails = null;
        if (user != null) {
            userDetails = new CustomUserDetails();
            userDetails.setUser(w);
        } else {
            throw new UsernameNotFoundException("User not exist with name : " + username);
        }
        return userDetails;

    }

}
