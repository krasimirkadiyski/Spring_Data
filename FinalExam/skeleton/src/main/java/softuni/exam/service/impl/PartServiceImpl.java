package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportPartsDto;
import softuni.exam.models.entity.Part;
import softuni.exam.repository.PartRepository;
import softuni.exam.service.PartService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.ValidationUtilImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static softuni.exam.util.OutputMessages.INVALID_PART;
import static softuni.exam.util.OutputMessages.SUCCESSFULLY_IMPORTED_PART;
import static softuni.exam.util.Paths.PARTS_JSON;

@Service
public class PartServiceImpl implements PartService {
    private final PartRepository partRepository;
    private final ModelMapper mapper;
    private final Gson gson;

    public PartServiceImpl(PartRepository partRepository, ModelMapper mapper, Gson gson) {
        this.partRepository = partRepository;
        this.mapper = mapper;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.partRepository.count() > 0;
    }

    @Override
    public String readPartsFileContent() throws IOException {
        return Files.readString(Path.of(PARTS_JSON));
    }

    @Override
    public String importParts() throws IOException {
        ValidationUtil validator = new ValidationUtilImpl();
        StringBuilder sb = new StringBuilder();
        ImportPartsDto[] dtos = gson.fromJson(readPartsFileContent(), ImportPartsDto[].class);
        for (ImportPartsDto dto : dtos) {
            if (isDuplicated(dto.getPartName()) || !validator.validate(dto)){
                sb.append(INVALID_PART)
                        .append(System.lineSeparator());
                continue;
            }
            Part part = mapper.map(dto, Part.class);
            this.partRepository.saveAndFlush(part);
            sb.append(String.format(SUCCESSFULLY_IMPORTED_PART, part.getPartName(), part.getPrice()))
                    .append(System.lineSeparator());
        }
        return sb.toString();
    }

    @Override
    public Boolean isDuplicated(String name) {
        return this.partRepository.findByPartName(name) != null;
    }

    @Override
    public Part getById(Long id) {
        return this.partRepository.findByIdIs(id);
    }
}
