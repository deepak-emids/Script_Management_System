package com.emids.sms.converter;

import com.emids.sms.dto.WriterDto;
import com.emids.sms.model.Writer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DtoConverter {
    public WriterDto writerToDto(Writer writer) {
        WriterDto writerDto = new WriterDto();

        writerDto.setName(writer.getName());
        writerDto.setAge(writer.getAge());
        writerDto.setGender(writer.getGender());

        return writerDto;
    }

    public Writer dtoToWriter(WriterDto writerDto) {
        Writer writer = new Writer();

        writer.setName(writerDto.getName());
        writer.setAge(writerDto.getAge());
        writer.setGender(writerDto.getGender());
        return writer;
    }

    public List<WriterDto> writerListToWriterDtoList(List<Writer> writers) {
        List<WriterDto> writerDtoList = writers.stream().map(Writer -> writerToDto(Writer))
                .collect(Collectors.toList());
        return writerDtoList;
    }
}