package com.anilcan.kartaca.model.domain;

import com.anilcan.kartaca.model.utils.Gender;

public record UserDomain(String firstName, String lastName, String userName, String password, Gender gender) {
}
