package com.emids.sms.controller;

import com.emids.sms.dto.ResponseDto;
import com.emids.sms.service.ScreenPlayService;
import com.emids.sms.service.WriterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.Arrays;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private WriterService writerService;

    @Autowired
    private ScreenPlayService screenPlayService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("public/writer/{id}")
    public ResponseEntity getWriter(@Valid @PathVariable("id") int id) {
        ResponseDto result = writerService.getWriter(id);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @GetMapping("public/screenplay/{id}")
    public ResponseEntity getScreenPlay(@Valid @PathVariable("id") int id) {
        ResponseDto result = screenPlayService.getScreenPlay(id);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @GetMapping("/public")
    public String test() {
        //log.info(passwordEncoder.encode("pass123"));
        return "test";
    }
}
