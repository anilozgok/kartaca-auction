package com.anilcan.kartaca.model.response;

import com.anilcan.kartaca.model.dto.UserInfoDTO;

import java.util.List;

public record UsersResponse(List<UserInfoDTO> userInfoDTOList) {

}
