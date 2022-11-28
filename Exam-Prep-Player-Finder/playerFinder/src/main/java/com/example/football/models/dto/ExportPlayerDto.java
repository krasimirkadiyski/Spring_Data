package com.example.football.models.dto;

import com.example.football.util.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExportPlayerDto {
    private String firstName;
    private String lastName;
    private Position position;
    private String teamName;
    private String stadiumName;

    @Override
    public String toString() {
        return String.format("""
                Player - %s %s
                    Position - %s
                    Team - %s
                    Stadium - %s
                """,firstName,lastName,position,teamName,stadiumName);
    }
}
