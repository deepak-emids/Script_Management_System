package com.emids.sms.repository;

import com.emids.sms.model.ScreenPlay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenPlayRepository extends JpaRepository<ScreenPlay, Integer> {
    ScreenPlay findByName(String email);
}
