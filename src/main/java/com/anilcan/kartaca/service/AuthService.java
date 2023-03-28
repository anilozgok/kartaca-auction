package com.anilcan.kartaca.service;

import com.anilcan.kartaca.model.dto.AuthDTO;
import com.anilcan.kartaca.model.dto.AuthenticateDTO;
import com.anilcan.kartaca.model.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public AuthDTO register(UserDTO userDTO) {

        log.info("Auth Service register Processing");

        var registeredUser = userService.register(new UserDTO(userDTO.userInfo()));

        return new AuthDTO(jwtService.generateToken(registeredUser.userName()));

    }

    public AuthDTO authenticate(AuthenticateDTO authenticateDTO) {

        log.info("Auth Service authenticate Processing");

        final var user = userService.findByUserName(authenticateDTO.userName());

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticateDTO.userName(),
                                                                                   authenticateDTO.password()));

        return new AuthDTO(jwtService.generateToken(user.getUsername()));
    }

}
