package com.example.football.models.dto.json;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImportTownDto {
    @Size(min = 2)
    @NotNull
    private String name;
    @Min(1)
    @NotNull
    private Long population;
    @Size(min = 10)
    @NotNull
    private String travelGuide;
}
