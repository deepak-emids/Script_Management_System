package com.emids.sms.service;


import com.emids.sms.repository.WriterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.emids.sms.model.Writer;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    WriterRepository writerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Writer writer = writerRepository.findByName(username);

        CustomUserDetails customUserDetails = null;
        if (writer != null) {
            customUserDetails = new CustomUserDetails();
            customUserDetails.setWriter(writer);
        } else {
            throw new UsernameNotFoundException("User not exist with name : " + username);
        }
        return customUserDetails;

    }
}