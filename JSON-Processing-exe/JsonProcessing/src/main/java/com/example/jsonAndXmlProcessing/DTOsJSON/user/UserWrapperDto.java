package com.example.jsonAndXmlProcessing.DTOsJSON.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserWrapperDto {
    private int count;
    private List<UserLiteInfoDto> users;

    public UserWrapperDto(List<UserLiteInfoDto> users) {
        this.users = users;
        this.count = users.size();
    }
}
