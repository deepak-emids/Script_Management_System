package com.emids.sms.service;

import java.time.LocalDateTime;
import java.util.*;

import com.emids.sms.dto.ResponseDto;
import com.emids.sms.exceptions.WriterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.emids.sms.dto.WriterDto;
import com.emids.sms.model.Writer;
import com.emids.sms.repository.WriterRepository;

@Slf4j
@Service
public class WriterService implements IWriterService {

    @Autowired
    public WriterRepository writerRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public ResponseDto addWriter(WriterDto writer) {
        ResponseDto responseDto = new ResponseDto();

        Writer foundWriter = writerRepository.findByName(writer.getName());

        if (foundWriter != null) {
            throw new WriterException("Writer already registered,Use Different Name.");
        } else {
            Writer newWriter = new Writer();

            newWriter.setName(writer.getName());
            newWriter.setAge(writer.getAge());
            newWriter.setGender(writer.getGender());
            newWriter.setRole(writer.getRole());

            String pwd = writer.getPassword();
            String encryptPwd = passwordEncoder.encode(pwd);

            newWriter.setPassword(encryptPwd);

            LocalDateTime createdAtTime = LocalDateTime.now();
            newWriter.setCreatedAt(createdAtTime);
            newWriter.setUpdatedAt(createdAtTime);

            Writer savedWriter = writerRepository.save(newWriter);
            savedWriter.setPassword("");
            responseDto.setData(savedWriter);
            responseDto.setStatus(200);
            responseDto.setMessage("Writer Registered");
            return responseDto;
        }
    }

    public ResponseDto getAllWriter() {
        ResponseDto responseDto = new ResponseDto();

        List<Writer> foundWriter = new ArrayList<>();
        writerRepository.findAll().forEach(foundWriter::add);

        if (foundWriter.size() == 0) {
            throw new WriterException("Writer Not Found");
        } else {
            responseDto.setData(foundWriter);
            responseDto.setStatus(200);
            responseDto.setMessage("Writers Fetched");
        }
        return responseDto;
    }

    @Override
    public ResponseDto getWriter(int id) {
        ResponseDto responseDto = new ResponseDto();

        Optional<Writer> foundWriter = writerRepository.findById(id);

        if (foundWriter.isEmpty()) {
            throw new WriterException("Writer Not Found");
        } else {
            foundWriter.get().setPassword("");
            responseDto.setData(foundWriter);
            responseDto.setMessage("Writer Found");
            responseDto.setStatus(200);
        }
        return responseDto;
    }

    @Override
    public ResponseDto updateWriter(WriterDto newData, int id) {
        ResponseDto responseDto = new ResponseDto();

        Optional<Writer> foundWriter = writerRepository.findById(id);

        if (foundWriter.isEmpty()) {
            throw new WriterException("Writer Not Found");
        } else {
            foundWriter.get().setAge(newData.getAge());
            foundWriter.get().setGender(newData.getGender());
            foundWriter.get().setRole(newData.getRole());

            String pwd = newData.getPassword();

            if (pwd != null) {
                String encryptPwd = passwordEncoder.encode(pwd);
                foundWriter.get().setPassword(encryptPwd);
            }

            Writer updated = writerRepository.save(foundWriter.get());
            updated.setPassword("");
            responseDto.setData(updated);
            responseDto.setMessage("Writer Updated");
            responseDto.setStatus(200);
        }
        return responseDto;
    }

    @Override
    public ResponseDto deleteWriter(int id) {
        ResponseDto responseDto = new ResponseDto();

        Optional<Writer> foundWriter = writerRepository.findById(id);

        if (foundWriter.isEmpty()) {
            throw new WriterException("Writer Not Found");
        } else {
            writerRepository.deleteById(id);
            responseDto.setData("");
            responseDto.setMessage("Writer Deleted");
            responseDto.setStatus(200);
        }
        return responseDto;
    }
}
