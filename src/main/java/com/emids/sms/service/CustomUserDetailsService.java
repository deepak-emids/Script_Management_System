package com.emids.sms.service;


import com.emids.sms.model.Writer;
import com.emids.sms.repository.WriterRepository;
import lombok.ToString;
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
        log.info("username...." + username);
        Writer user = repository.findByName(username);
        log.info("founduser...." + String.valueOf(user.getRoles()));

        Writer w = new Writer();
        w.setName(user.getName());
        w.setPassword(user.getPassword());
        w.setRoles(user.getRoles());
        log.info("nwew user....." + String.valueOf(w));

        CustomUserDetails userDetails = null;
        if (user != null) {
            userDetails = new CustomUserDetails();
            userDetails.setUser(w);
        } else {
            throw new UsernameNotFoundException("User not exist with name : " + username);
        }

        log.info("returning userDetails from CustomUserDetailsService" + String.valueOf(userDetails));
        return userDetails;

    }

}
