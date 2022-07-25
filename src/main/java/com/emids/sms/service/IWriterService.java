package com.emids.sms.service;

import com.emids.sms.dto.ResponseDto;
import com.emids.sms.dto.WriterDto;


public interface IWriterService {

    ResponseDto addWriter(WriterDto Writer);

    ResponseDto getAllWriter();

    ResponseDto getWriter(int id);

    ResponseDto updateWriter(WriterDto Writer, int id);

    ResponseDto deleteWriter(int id);

}

