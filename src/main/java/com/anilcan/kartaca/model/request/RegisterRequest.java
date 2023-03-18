package com.anilcan.kartaca.model.request;

import com.anilcan.kartaca.model.domain.UserDomain;

public record RegisterRequest(UserDomain userInfo) {
}
