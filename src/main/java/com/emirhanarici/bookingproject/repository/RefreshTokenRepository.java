package com.emirhanarici.bookingproject.repository;

import com.emirhanarici.bookingproject.model.RefreshToken;
import com.emirhanarici.bookingproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>{
    RefreshToken findByUserId(Long userId);


    Optional<RefreshToken> findByToken(String token);


    @Modifying
    int deleteByUser(User user);
}
