package com.emirhanarici.bookingproject.service;

import com.emirhanarici.bookingproject.exception.user.UserNotFoundException;
import com.emirhanarici.bookingproject.model.RefreshToken;
import com.emirhanarici.bookingproject.model.User;
import com.emirhanarici.bookingproject.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    @Value("${jwt.refrEshexpireMs}")
    Long expireSeconds;

    private final RefreshTokenRepository refreshTokenRepository;

    private final UserService userService;


    public String createRefreshToken(User user) {

        RefreshToken token = getByUser(user.getId());

        if (token == null) {
            token = new RefreshToken();
            token.setUser(user);
        }

        token.setToken(UUID.randomUUID().toString());
        token.setExpiryDate(Instant.now().plusSeconds(expireSeconds).atZone(ZoneOffset.UTC).toLocalDate());
        refreshTokenRepository.save(token);

        return token.getToken();
    }


    public boolean isRefreshExpired(RefreshToken token) {
        return token.getExpiryDate().isBefore(LocalDate.now());
    }


    public RefreshToken getByUser(Long userId) {
        return refreshTokenRepository.findByUserId(userId);
    }


    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }


    @Transactional
    public int deleteByUserId(Long userId) {

        User user = userService.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        return refreshTokenRepository.deleteByUser(user);
    }


}
