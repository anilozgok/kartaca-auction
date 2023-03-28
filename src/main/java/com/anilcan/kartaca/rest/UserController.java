package com.anilcan.kartaca.rest;

import com.anilcan.kartaca.model.response.UsersResponse;
import com.anilcan.kartaca.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<UsersResponse> getAllUsers() {
        log.info("Get All Users Caught");
        var users = userService.getAllUsers();
        return new ResponseEntity<>(new UsersResponse(users), HttpStatus.OK);
    }

}
