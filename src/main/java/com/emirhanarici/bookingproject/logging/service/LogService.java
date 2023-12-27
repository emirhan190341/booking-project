package com.emirhanarici.bookingproject.logging.service;

import com.emirhanarici.bookingproject.logging.entity.LogEntity;
import com.emirhanarici.bookingproject.logging.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LogService {

    private final LogRepository logRepository;

    public void saveLogToDatabase(LogEntity logEntity) {
        logEntity.setTime(LocalDateTime.now());
        logRepository.save(logEntity);
    }

}
