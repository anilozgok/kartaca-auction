package com.anilcan.kartaca.rest;

import com.anilcan.kartaca.model.dto.UserDTO;
import com.anilcan.kartaca.model.entity.User;
import com.anilcan.kartaca.model.request.LoginRequest;
import com.anilcan.kartaca.model.request.RegisterRequest;
import com.anilcan.kartaca.model.response.RegisteredResponse;
import com.anilcan.kartaca.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegisteredResponse> register(@RequestBody RegisterRequest registerRequest) {

        log.info("register request caught.");

        var registeredDto = userService.register(new UserDTO(registerRequest.userInfo()));

        return new ResponseEntity<>(new RegisteredResponse(registeredDto), HttpStatus.OK);

    }

    @GetMapping
    public String main(){
        return "main";
    }

/*
    public void login(@RequestBody LoginRequest loginRequest) {

        log.info("login request caught.");

    }


    public void logout() {

        log.info("logout request caught.");

    }*/

}
