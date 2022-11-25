package com.example.jsonprocessing.DTOs.user;

import java.util.List;
import java.util.Set;

public class UserWrapperDto {
    private int count;
    private List<UserLiteInfoDto> users;

    public UserWrapperDto(List<UserLiteInfoDto> users) {
        this.users = users;
        this.count = users.size();
    }
}
