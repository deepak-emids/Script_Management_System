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
import org.springframework.security.core.context.SecurityContextHolder;
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

            foundScreenPlay.setName(screenPlay.getName());
            foundScreenPlay.setGenre(screenPlay.getGenre());
            foundScreenPlay.setDescription(screenPlay.getDescription());

            LocalDateTime createdAtTime = LocalDateTime.now();
            foundScreenPlay.setCreatedAt(createdAtTime);
            foundScreenPlay.setUpdatedAt(createdAtTime);

            String user = SecurityContextHolder.getContext().getAuthentication().getName();

            Writer currentWriter = writerRepository.findByName(user);

            if (foundScreenPlay.getWriter() != null && !foundScreenPlay.getWriter().isEmpty()) {
                foundScreenPlay.getWriter().add(currentWriter);
            } else {
                Set<Writer> writerSet = new HashSet<>();
                writerSet.add(currentWriter);
                foundScreenPlay.setWriter(writerSet);
            }

            ScreenPlay savedScreenPlay = screenPlayRepository.save(foundScreenPlay);
            responseDto.setData(savedScreenPlay);
            responseDto.setStatus(200);
            responseDto.setMessage("Screen Play Updated");
            return responseDto;
        } else {
            ScreenPlay newScreenplay = new ScreenPlay();

            newScreenplay.setName(screenPlay.getName());
            newScreenplay.setGenre(screenPlay.getGenre());
            newScreenplay.setDescription(screenPlay.getDescription());

            LocalDateTime createdAtTime = LocalDateTime.now();
            newScreenplay.setCreatedAt(createdAtTime);
            newScreenplay.setUpdatedAt(createdAtTime);

            String user = SecurityContextHolder.getContext().getAuthentication().getName();

            Writer currentWriter = writerRepository.findByName(user);

            if (newScreenplay.getWriter() != null && !newScreenplay.getWriter().isEmpty()) {
                newScreenplay.getWriter().add(currentWriter);
            } else {
                Set<Writer> writerSet = new HashSet<>();
                writerSet.add(currentWriter);
                newScreenplay.setWriter(writerSet);
            }

            ScreenPlay savedScreenPlay = screenPlayRepository.save(newScreenplay);
            responseDto.setData(savedScreenPlay);
            responseDto.setStatus(200);
            responseDto.setMessage("New Screen Play Added");
            return responseDto;
        }
    }

    public ResponseDto getAllScreenPlay() {
        ResponseDto responseDto = new ResponseDto();

        List<ScreenPlay> foundScreenPlay = new ArrayList<>();
        screenPlayRepository.findAll().forEach(foundScreenPlay::add);

        if (foundScreenPlay.size() == 0) {
            throw new ScreenPlayException("Screen Play Not Found");
        } else {

            responseDto.setData(foundScreenPlay);
            responseDto.setStatus(200);
            responseDto.setMessage("Screen Plays Fetched");
        }
        return responseDto;
    }

    @Override
    public ResponseDto getScreenPlay(int id) {
        ResponseDto responseDto = new ResponseDto();

        Optional<ScreenPlay> foundScreenPlay = screenPlayRepository.findById(id);

        if (foundScreenPlay.isEmpty()) {
            throw new ScreenPlayException("Screen Play Not Found");
        } else {
            List<String> names = foundScreenPlay
                    .get()
                    .getWriter()
                    .stream()
                    .map(f -> f.getName())
                    .collect(Collectors.toList());

            foundScreenPlay.get().setWriters(names);

            responseDto.setData(foundScreenPlay);
            responseDto.setMessage("screenPlay Found");
            responseDto.setStatus(200);
        }
        return responseDto;
    }

    @Override
    public ResponseDto updateScreenPlay(ScreenPlayDto emp, int id) {
        ResponseDto responseDto = new ResponseDto();

        Optional<ScreenPlay> foundScreenPlay = screenPlayRepository.findById(id);

        if (foundScreenPlay.isEmpty()) {
            throw new ScreenPlayException("screenPlay Not Found");
        } else {
            foundScreenPlay.get().setName(emp.getName());
            foundScreenPlay.get().setGenre(emp.getGenre());
            foundScreenPlay.get().setDescription(emp.getDescription());

            LocalDateTime createdAtTime = LocalDateTime.now();
            foundScreenPlay.get().setUpdatedAt(createdAtTime);

            ScreenPlay updated = screenPlayRepository.save(foundScreenPlay.get());
            responseDto.setData(updated);
            responseDto.setMessage("screenPlay Updated");
            responseDto.setStatus(200);
        }
        return responseDto;
    }

    @Override
    public ResponseDto deleteScreenPlay(int id) {
        ResponseDto responseDto = new ResponseDto();

        Optional<ScreenPlay> foundScreenPlay = screenPlayRepository.findById(id);

        if (foundScreenPlay.isEmpty()) {
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
