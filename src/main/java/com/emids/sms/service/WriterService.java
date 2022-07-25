package com.emids.sms.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.emids.sms.dto.ResponseDto;
import com.emids.sms.exceptions.WriterException;
import com.emids.sms.exceptions.ExceptionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emids.sms.dto.WriterDto;
import com.emids.sms.model.Writer;
import com.emids.sms.repository.WriterRepository;

@Service
public class WriterService implements IWriterService {

    @Autowired
    public WriterRepository writerRepository;

    @Autowired
    ResponseDto responseDto;

    @Override
    public ResponseDto addWriter(WriterDto writer) {

        Writer foundWriter = writerRepository.findByName(writer.getName());

        if (foundWriter != null) {
            throw new WriterException("Name already registered, Use Different Name.", ExceptionType.CONFLICT);
        } else {
            Writer emp = new Writer();

            emp.setName(writer.getName());
            emp.setAge(writer.getAge());
            emp.setGender(writer.getGender());

            LocalDateTime createdAtTime = LocalDateTime.now();
            emp.setCreatedAt(createdAtTime);
            emp.setUpdatedAt(createdAtTime);

            Writer saved = writerRepository.save(emp);
            responseDto.setData(saved);
            responseDto.setStatus(200);
            responseDto.setMessage("Writer Registered");
            return responseDto;
        }
    }

    public ResponseDto getAllWriter() {
        List<Writer> writer = new ArrayList<>();
        writerRepository.findAll().forEach(writer::add);

        if (writer.size() == 0) {
            throw new WriterException("Writer Not Found", ExceptionType.NOT_FOUND);
        } else {
            responseDto.setData(writer);
            responseDto.setStatus(200);
            responseDto.setMessage("Writers Fetched");
        }
        return responseDto;
    }

    @Override
    public ResponseDto getWriter(int id) {
        Optional<Writer> writer = writerRepository.findById(id);

        if (writer.isEmpty()) {
           
            throw new WriterException("Writer Not Found", ExceptionType.NOT_FOUND);
        } else {
            responseDto.setData(writer);
            responseDto.setMessage("Writer Found");
            responseDto.setStatus(200);
        }
        return responseDto;
    }

    @Override
    public ResponseDto updateWriter(WriterDto emp, int id) {
        Optional<Writer> writer = writerRepository.findById(id);

        if (writer.isEmpty()) {
            throw new WriterException("Writer Not Found", ExceptionType.NOT_FOUND);
        } else {
            writer.get().setName(emp.getName());
            writer.get().setAge(emp.getAge());

            Writer updated = writerRepository.save(writer.get());
            responseDto.setData(updated);
            responseDto.setMessage("Writer Updated");
            responseDto.setStatus(200);
        }
        return responseDto;
    }

    @Override
    public ResponseDto deleteWriter(int id) {
        Optional<Writer> writer = writerRepository.findById(id);

        System.out.println(writer);
        if (writer.isEmpty()) {
            throw new WriterException("Writer Not Found", ExceptionType.NOT_FOUND);
        } else {
            writerRepository.deleteById(id);
            responseDto.setData("");
            responseDto.setMessage("Writer Deleted");
            responseDto.setStatus(200);
        }
        return responseDto;
    }
}
