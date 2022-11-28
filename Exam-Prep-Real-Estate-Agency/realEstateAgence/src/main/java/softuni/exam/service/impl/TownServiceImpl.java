package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.jsonDto.ImportTownDto;
import softuni.exam.repository.TownRepository;
import softuni.exam.repository.entity.Town;
import softuni.exam.service.TownService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.ValidationUtilImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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
        return townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files.readString(Path.of("src/main/resources/files/json/towns.json"));
    }

    @Override
    public String importTowns() throws IOException {
        StringBuilder sb = new StringBuilder();
        ValidationUtil validator = new ValidationUtilImpl();
        ImportTownDto[] dtos = gson.fromJson(readTownsFileContent(), ImportTownDto[].class);
        for (ImportTownDto dto : dtos) {

                if (validator.validate(dto)){
                    Town town = modelMapper.map(dto, Town.class);
                    townRepository.saveAndFlush(town);
                    sb.append(String.format("Successfully imported town %s - %s",town.getTownName(),town.getPopulation()))
                            .append(System.lineSeparator());
                }else {
                    sb.append("Invalid town").append(System.lineSeparator());
                }
        }
        return sb.toString();
    }

    @Override
    public Town getByName(String name) {
       return townRepository.findByTownName(name);
    }


}
