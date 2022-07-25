package com.emids.sms.service;

import com.emids.sms.dto.ResponseDto;
import com.emids.sms.dto.ScreenPlayDto;

public interface IScreenPlayService {

    ResponseDto addScreenPlay(ScreenPlayDto screenPlay);

    ResponseDto getAllScreenPlay();

    ResponseDto getScreenPlay(int id);

    ResponseDto updateScreenPlay(ScreenPlayDto screenPlay, int id);

    ResponseDto deleteScreenPlay(int id);

}

