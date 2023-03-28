package com.anilcan.kartaca.rest;

import com.anilcan.kartaca.model.dto.UserDTO;
import com.anilcan.kartaca.model.request.AuthenticateRequest;
import com.anilcan.kartaca.model.request.RegisterRequest;
import com.anilcan.kartaca.model.response.AuthResponse;
import com.anilcan.kartaca.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthContoller {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) {
        log.info("Register Request Caught");
        var authDto = authService.register(new UserDTO(registerRequest.userInfo()));
        return new ResponseEntity<>(new AuthResponse(authDto), HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthenticateRequest authenticateRequest) {
        log.info("Authenticate Request Caught");
        var authDto = authService.authenticate(authenticateRequest.loginInfo());
        return new ResponseEntity<>(new AuthResponse(authDto), HttpStatus.OK);
    }

}
