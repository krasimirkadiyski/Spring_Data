package com.example.football.service.impl;

import com.example.football.models.dto.json.ImportTownDto;
import com.example.football.models.entity.Town;
import com.example.football.repository.TownRepository;
import com.example.football.service.TownService;
import com.example.football.util.ValidationUtil;
import com.example.football.util.ValidationUtilImpl;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.example.football.Paths.ImportUrls.IMPORT_TOWN_URL;
import static com.example.football.util.OutputMessage.INVALID_TOWN;
import static com.example.football.util.OutputMessage.SUCCESSFULLY_IMPORTED_TOWN;


@Service
public class TownServiceImpl implements TownService {
    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;

    public TownServiceImpl(TownRepository townRepository, ModelMapper modelMapper, Gson gson) {
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files.readString(Path.of(IMPORT_TOWN_URL));
    }

    @Override
    public String importTowns() throws IOException {
        ValidationUtil validator = new ValidationUtilImpl();
        StringBuilder sb = new StringBuilder();
        ImportTownDto[] dtos = gson.fromJson(readTownsFileContent(), ImportTownDto[].class);
        for (ImportTownDto dto : dtos) {
            if (townRepository.findByName(dto.getName()) != null || !validator.validate(dto)) {
                sb.append(INVALID_TOWN)
                        .append(System.lineSeparator());
                continue;
            }
            Town town = modelMapper.map(dto, Town.class);
            townRepository.saveAndFlush(town);
            sb.append(String.format(SUCCESSFULLY_IMPORTED_TOWN, town.getName(), town.getPopulation()))
                    .append(System.lineSeparator());

        }
        return sb.toString();
    }

    @Override
    public Town getByName(String name) {
        return townRepository.findByName(name);
    }
}
