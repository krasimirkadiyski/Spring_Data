package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportMechanicsDto;
import softuni.exam.models.entity.Mechanic;
import softuni.exam.repository.MechanicRepository;
import softuni.exam.service.MechanicService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.ValidationUtilImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static softuni.exam.util.OutputMessages.INVALID_MECHANIC;
import static softuni.exam.util.OutputMessages.SUCCESSFULLY_IMPORTED_MECHANIC;
import static softuni.exam.util.Paths.MECHANICS_JSON;

@Service
public class MechanicServiceImpl implements MechanicService {
    private final MechanicRepository mechanicRepository;
    private final ModelMapper mapper;
    private final Gson gson;

    public MechanicServiceImpl(MechanicRepository mechanicRepository, ModelMapper mapper, Gson gson) {
        this.mechanicRepository = mechanicRepository;
        this.mapper = mapper;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.mechanicRepository.count() > 0;
    }

    @Override
    public String readMechanicsFromFile() throws IOException {
        return Files.readString(Path.of(MECHANICS_JSON));
    }

    @Override
    public String importMechanics() throws IOException {
        StringBuilder sb = new StringBuilder();
        ValidationUtil validator = new ValidationUtilImpl();
        ImportMechanicsDto[] dtos = gson.fromJson(readMechanicsFromFile(), ImportMechanicsDto[].class);
        for (ImportMechanicsDto dto : dtos) {
            if (!validator.validate(dto) || isDuplicatedByEmail(dto.getEmail())){
                sb.append(INVALID_MECHANIC)
                        .append(System.lineSeparator());
                continue;
            }
            Mechanic mechanic = mapper.map(dto, Mechanic.class);
            this.mechanicRepository.saveAndFlush(mechanic);
            sb.append(String.format(SUCCESSFULLY_IMPORTED_MECHANIC,mechanic.getFirstName(),mechanic.getLastName()))
                    .append(System.lineSeparator());
        }
        return sb.toString();
    }

    @Override
    public Boolean isDuplicatedByEmail(String email) {
        return this.mechanicRepository.findByEmail(email) != null;
    }

    @Override
    public Boolean isDuplicatedByFirstName(String firstName) {
       return this.mechanicRepository.findByFirstName(firstName) != null;
    }

    @Override
    public Mechanic getByFirstName(String firstName) {
        return this.mechanicRepository.findByFirstName(firstName);
    }
}
