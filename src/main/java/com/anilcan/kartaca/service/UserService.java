package com.anilcan.kartaca.service;

import com.anilcan.kartaca.exception.UserAlreadyRegisteredException;
import com.anilcan.kartaca.exception.UserNotFoundException;
import com.anilcan.kartaca.model.dto.RegisteredDTO;
import com.anilcan.kartaca.model.dto.UserDTO;
import com.anilcan.kartaca.model.entity.User;
import com.anilcan.kartaca.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public RegisteredDTO register(UserDTO userDto) {

        log.info("register request processing");

        if (userRepository.findByUserName(userDto.userInfo().userName()).isPresent()) throw new UserAlreadyRegisteredException();

        //        var hashedPassword = bCryptPasswordEncoder.encode(userDto.userInfo().password());

        var user = User.builder()
                       .firstName(userDto.userInfo().firstName())
                       .lastName(userDto.userInfo().lastName())
                       .userName(userDto.userInfo().userName())
                       .hashedPassword(userDto.userInfo().password())
                       .gender(userDto.userInfo().gender())
                       .build();

        var savedUser = userRepository.save(user);

        return new RegisteredDTO(savedUser.getId(), savedUser.getUserName());
    }

    public User findByUserName(String userName) {

        return userRepository.findByUserName(userName).orElseThrow(UserNotFoundException::new);
    }

}
