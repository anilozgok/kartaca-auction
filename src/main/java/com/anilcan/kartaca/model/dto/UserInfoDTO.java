package com.anilcan.kartaca.model.dto;

import com.anilcan.kartaca.enums.Gender;

import java.time.LocalDateTime;

public record UserInfoDTO(String firstName, String lastName, String userName, Gender gender, LocalDateTime registeredAt) {

}
