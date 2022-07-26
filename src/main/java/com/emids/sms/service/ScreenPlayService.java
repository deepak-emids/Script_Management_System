package com.emids.sms.service;

import com.emids.sms.dto.ResponseDto;
import com.emids.sms.dto.ScreenPlayDto;
import com.emids.sms.exceptions.ScreenPlayException;
import com.emids.sms.model.ScreenPlay;
import com.emids.sms.model.Writer;
import com.emids.sms.repository.ScreenPlayRepository;
import com.emids.sms.repository.WriterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ScreenPlayService implements IScreenPlayService {

    @Autowired
    public ScreenPlayRepository screenPlayRepository;

    @Autowired
    public WriterRepository writerRepository;

    @Override
    public ResponseDto addScreenPlay(ScreenPlayDto screenPlay) {
        ResponseDto responseDto = new ResponseDto();

        ScreenPlay foundScreenPlay = screenPlayRepository.findByName(screenPlay.getName());

        if (foundScreenPlay != null) {
            throw new ScreenPlayException("Screen already present, Use Different Name");
        } else {

            ScreenPlay sp = new ScreenPlay();

            sp.setName(screenPlay.getName());
            sp.setGenre(screenPlay.getGenre());
            sp.setDescription(screenPlay.getDescription());

            LocalDateTime createdAtTime = LocalDateTime.now();
            sp.setCreatedAt(createdAtTime);
            sp.setUpdatedAt(createdAtTime);

            Writer writer = writerRepository.findByName("deep");

            writer.getScreenplay().add(sp);

            Writer saved = writerRepository.save(writer);
            responseDto.setData(saved);
            responseDto.setStatus(200);
            responseDto.setMessage("Screen Play Registered");
            return responseDto;
        }
    }

    public ResponseDto getAllScreenPlay() {
        ResponseDto responseDto = new ResponseDto();

        List<ScreenPlay> screenPlay = new ArrayList<>();
        screenPlayRepository.findAll().forEach(screenPlay::add);

        if (screenPlay.size() == 0) {
            throw new ScreenPlayException("Screen Play Not Found");
        } else {

            responseDto.setData(screenPlay);
            responseDto.setStatus(200);
            responseDto.setMessage("Screen Plays Fetched");
        }
        return responseDto;
    }

    @Override
    public ResponseDto getScreenPlay(int id) {
        ResponseDto responseDto = new ResponseDto();

        Optional<ScreenPlay> screenPlay = screenPlayRepository.findById(id);

        if (screenPlay.isEmpty()) {
            throw new ScreenPlayException("Screen Play Not Found");
        } else {
            List<String> names = screenPlay
                    .get()
                    .getWriter()
                    .stream()
                    .map(f -> f.getName())
                    .collect(Collectors.toList());

            responseDto.setData(screenPlay.get());
            responseDto.setMessage("screenPlay Found");
            responseDto.setStatus(200);
        }
        return responseDto;
    }

    @Override
    public ResponseDto updateScreenPlay(ScreenPlayDto emp, int id) {
        ResponseDto responseDto = new ResponseDto();

        Optional<ScreenPlay> screenPlay = screenPlayRepository.findById(id);

        if (screenPlay.isEmpty()) {
            throw new ScreenPlayException("screenPlay Not Found");
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
        ResponseDto responseDto = new ResponseDto();

        Optional<ScreenPlay> screenPlay = screenPlayRepository.findById(id);

        if (screenPlay.isEmpty()) {
            throw new ScreenPlayException("screenPlay Not Found");
        } else {
            screenPlayRepository.deleteById(id);
            responseDto.setData("");
            responseDto.setMessage("screenPlay Deleted");
            responseDto.setStatus(200);
        }
        return responseDto;
    }
}
