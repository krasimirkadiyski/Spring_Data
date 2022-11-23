package com.example.jsonprocessing.DTOs.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImportUserDto {
    private String firstName;
    private String lastName;
    private Integer age;
}
