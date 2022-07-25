package com.emids.sms.service;

import com.emids.sms.dto.ResponseDto;
import com.emids.sms.dto.ScreenPlayDto;
import com.emids.sms.exceptions.ExceptionType;
import com.emids.sms.exceptions.ScreenPlayException;
import com.emids.sms.model.ScreenPlay;
import com.emids.sms.repository.ScreenPlayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ScreenPlayService implements IScreenPlayService {

    @Autowired
    public ScreenPlayRepository screenPlayRepository;

    @Autowired
    ResponseDto responseDto;

    @Override
    public ResponseDto addScreenPlay(ScreenPlayDto screenPlay) {

        ScreenPlay foundScreenPlay = screenPlayRepository.findByName(screenPlay.getName());

        if (foundScreenPlay != null) {
            throw new ScreenPlayException("Name already present, Use Different Name.", ExceptionType.CONFLICT);
        } else {
            ScreenPlay sp = new ScreenPlay();

            sp.setName(screenPlay.getName());
            sp.setGenre(screenPlay.getGenre());
            sp.setDescription(screenPlay.getDescription());

            LocalDateTime createdAtTime = LocalDateTime.now();
            sp.setCreatedAt(createdAtTime);
            sp.setUpdatedAt(createdAtTime);

            ScreenPlay saved = screenPlayRepository.save(sp);
            responseDto.setData(saved);
            responseDto.setStatus(200);
            responseDto.setMessage("screenPlay Registered");
            return responseDto;
        }
    }

    public ResponseDto getAllScreenPlay() {
        List<ScreenPlay> screenPlay = new ArrayList<>();
        screenPlayRepository.findAll().forEach(screenPlay::add);

        if (screenPlay.size() == 0) {
            throw new ScreenPlayException("screenPlay Not Found", ExceptionType.NOT_FOUND);
        } else {
            responseDto.setData(screenPlay);
            responseDto.setStatus(200);
            responseDto.setMessage("screenPlays Fetched");
        }
        return responseDto;
    }

    @Override
    public ResponseDto getScreenPlay(int id) {
        Optional<ScreenPlay> screenPlay = screenPlayRepository.findById(id);

        if (screenPlay.isEmpty()) {
            throw new ScreenPlayException("screenPlay Not Found", ExceptionType.NOT_FOUND);
        } else {
            responseDto.setData(screenPlay);
            responseDto.setMessage("screenPlay Found");
            responseDto.setStatus(200);
        }
        return responseDto;
    }

    @Override
    public ResponseDto updateScreenPlay(ScreenPlayDto emp, int id) {
        Optional<ScreenPlay> screenPlay = screenPlayRepository.findById(id);

        if (screenPlay.isEmpty()) {
            throw new ScreenPlayException("screenPlay Not Found", ExceptionType.NOT_FOUND);
        } else {
            screenPlay.get().setName(emp.getName());
            screenPlay.get().setGenre(emp.getGenre());

            ScreenPlay updated = screenPlayRepository.save(screenPlay.get());
            responseDto.setData(updated);
            responseDto.setMessage("screenPlay Updated");
            responseDto.setStatus(200);
        }
        return responseDto;
    }

    @Override
    public ResponseDto deleteScreenPlay(int id) {
        Optional<ScreenPlay> screenPlay = screenPlayRepository.findById(id);

        if (screenPlay.isEmpty()) {
            throw new ScreenPlayException("screenPlay Not Found", ExceptionType.NOT_FOUND);
        } else {
            screenPlayRepository.deleteById(id);
            responseDto.setData("");
            responseDto.setMessage("screenPlay Deleted");
            responseDto.setStatus(200);
        }
        return responseDto;
    }
}
