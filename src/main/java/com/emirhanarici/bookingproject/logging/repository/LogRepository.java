package com.emirhanarici.bookingproject.logging.repository;

import com.emirhanarici.bookingproject.logging.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<LogEntity,Long> {
}
