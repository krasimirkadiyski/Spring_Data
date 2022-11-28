package com.example.football.service.impl;

import com.example.football.models.dto.json.ImportTeamDto;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.TeamRepository;
import com.example.football.service.TeamService;
import com.example.football.service.TownService;
import com.example.football.util.ValidationUtil;
import com.example.football.util.ValidationUtilImpl;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.example.football.Paths.ImportUrls.IMPORT_TEAM_URL;
import static com.example.football.util.OutputMessage.INVALID_TEAM;
import static com.example.football.util.OutputMessage.SUCCESSFULLY_IMPORTED_TEAM;

@Service
public class TeamServiceImpl implements TeamService {
private final TeamRepository teamRepository;
private final TownService townService;
private final ModelMapper modelMapper;
private final Gson gson;

    public TeamServiceImpl(TeamRepository teamRepository,TownService townService, ModelMapper modelMapper, Gson gson) {
        this.teamRepository = teamRepository;
        this.townService = townService;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.teamRepository.count() > 0;
    }

    @Override
    public String readTeamsFileContent() throws IOException {
        return Files.readString(Path.of(IMPORT_TEAM_URL));
    }

    @Override
    public String importTeams() throws IOException {
        StringBuilder sb = new StringBuilder();
        ValidationUtil validator = new ValidationUtilImpl();
        ImportTeamDto[] dtos = gson.fromJson(readTeamsFileContent(), ImportTeamDto[].class);
        for (ImportTeamDto dto : dtos) {
            if (!validator.validate(dto) || teamRepository.findByName(dto.getName()) != null){
                sb.append(INVALID_TEAM)
                        .append(System.lineSeparator());
                continue;
            }
            Team team = modelMapper.map(dto, Team.class);
            Town town = townService.getByName(dto.getTownName());
            team.setTown(town);
            sb.append(String.format(SUCCESSFULLY_IMPORTED_TEAM,team.getName(),team.getFanBase()))
                    .append(System.lineSeparator());
            teamRepository.saveAndFlush(team);

        }
        return sb.toString();
    }

    @Override
    public Team getByName(String name) {
       return teamRepository.findByName(name);
    }


}
