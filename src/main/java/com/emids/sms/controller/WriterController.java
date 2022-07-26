package com.emids.sms.controller;

import com.emids.sms.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.emids.sms.dto.WriterDto;
import com.emids.sms.service.WriterService;

import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/writer")
public class WriterController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private WriterService writerService;

    @GetMapping()
    public String test() {
        //log.info(passwordEncoder.encode("pass123"));

        return "test";
    }

    @PostMapping()
    public ResponseEntity addWriter(@RequestBody WriterDto writer) {
        log.info("writer body....." + writer);
        ResponseDto result = writerService.addWriter(writer);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

//    @GetMapping()
//    public ResponseEntity getAllWriters() {
//        ResponseDto result = writerService.getAllWriter();
//        return ResponseEntity.status(result.getStatus()).body(result);
//    }

//    @GetMapping("/{id}")
//    public ResponseEntity getWriter(@Valid @PathVariable("id") int id) {
//        ResponseDto result = writerService.getWriter(id);
//        return ResponseEntity.status(result.getStatus()).body(result);
//    }

    @PutMapping("/{id}")
    public ResponseEntity updateWriter(@Valid @RequestBody WriterDto writer, @Valid @PathVariable("id") int id) {
        ResponseDto result = writerService.updateWriter(writer, id);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteWriters(@Valid @PathVariable("id") int id) {
        ResponseDto result = writerService.deleteWriter(id);
        return ResponseEntity.status(result.getStatus()).body(result);
    }
}
