package com.anilcan.kartaca.service;

import com.anilcan.kartaca.enums.Role;
import com.anilcan.kartaca.exception.UserAlreadyRegisteredException;
import com.anilcan.kartaca.exception.UserNotFoundException;
import com.anilcan.kartaca.model.dto.RegisteredDTO;
import com.anilcan.kartaca.model.dto.UserDTO;
import com.anilcan.kartaca.model.dto.UserInfoDTO;
import com.anilcan.kartaca.model.entity.User;
import com.anilcan.kartaca.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public RegisteredDTO register(UserDTO userDto) {

        log.info("Register Request Processing");

        if (userRepository.findByUserName(userDto.userInfo().userName()).isPresent()) throw new UserAlreadyRegisteredException();

        var user = User.builder()
                       .firstName(userDto.userInfo().firstName())
                       .lastName(userDto.userInfo().lastName())
                       .userName(userDto.userInfo().userName())
                       .hashedPassword(passwordEncoder.encode(userDto.userInfo().password()))
                       .gender(userDto.userInfo().gender())
                       .role(Role.USER)
                       .registeredAt(LocalDateTime.now())
                       .build();

        var savedUser = userRepository.save(user);

        return new RegisteredDTO(savedUser.getId(), savedUser.getUsername());
    }

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName).orElseThrow(UserNotFoundException::new);
    }

    public List<UserInfoDTO> getAllUsers() {
        log.info("Get All Users Processing");
        return userRepository.findAll()
                             .stream()
                             .map(user -> new UserInfoDTO(user.getFirstName(),
                                                          user.getLastName(),
                                                          user.getUsername(),
                                                          user.getGender(),
                                                          user.getRegisteredAt()))
                             .collect(Collectors.toList());
    }

}
