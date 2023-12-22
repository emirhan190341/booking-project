package com.emirhanarici.bookingproject.controller;

import com.emirhanarici.bookingproject.payload.request.auth.LoginRequest;
import com.emirhanarici.bookingproject.payload.request.auth.SignupRequest;
import com.emirhanarici.bookingproject.payload.response.CustomResponse;
import com.emirhanarici.bookingproject.payload.response.auth.JWTResponse;
import com.emirhanarici.bookingproject.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse<String> register(@RequestBody SignupRequest request) {

        return CustomResponse.created(authService.register(request));
    }

    @PostMapping("/login")
    public CustomResponse<JWTResponse> login(@RequestBody LoginRequest request) {

        return CustomResponse.ok(authService.login(request));
    }

}
