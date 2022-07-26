package com.emids.sms.controller;

import com.emids.sms.dto.ResponseDto;
import com.emids.sms.dto.ScreenPlayDto;
import com.emids.sms.service.ScreenPlayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Slf4j
@RestController
public class ScreenPlayController {

    @Autowired
    private ScreenPlayService screenPlayService;

    @PostMapping("/screenplay")
    public ResponseEntity addScreenPlay(@Valid @RequestBody ScreenPlayDto screenPlay) {
        ResponseDto result = screenPlayService.addScreenPlay(screenPlay);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @GetMapping("/screenplay")
    public ResponseEntity getAllScreenPlays() {
        ResponseDto result = screenPlayService.getAllScreenPlay();
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @PutMapping("screenplay/{id}")
    public ResponseEntity updateScreenPlay(@Valid @RequestBody ScreenPlayDto screenPlay, @Valid @PathVariable("id") int id) {
        ResponseDto result = screenPlayService.updateScreenPlay(screenPlay, id);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @DeleteMapping("screenplay/{id}")
    public ResponseEntity deleteScreenPlays(@Valid @PathVariable("id") int id) {
        ResponseDto result = screenPlayService.deleteScreenPlay(id);
        return ResponseEntity.status(result.getStatus()).body(result);
    }
}
