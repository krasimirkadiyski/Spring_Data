package com.example.football.service.impl;

import com.example.football.models.dto.xml.ImportStatDto;
import com.example.football.models.dto.xml.ImportStatWrapper;
import com.example.football.models.entity.Stat;
import com.example.football.repository.StatRepository;
import com.example.football.service.StatService;
import com.example.football.util.ValidationUtil;
import com.example.football.util.ValidationUtilImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static com.example.football.Paths.ImportUrls.IMPORT_STAT_URL;
import static com.example.football.util.OutputMessage.INVALID_STAT;
import static com.example.football.util.OutputMessage.SUCCESSFULLY_IMPORTED_STAT;

@Service
public class StatServiceImpl implements StatService {
    private final StatRepository statRepository;
    private final ModelMapper modelMapper;

    public StatServiceImpl(StatRepository statRepository, ModelMapper modelMapper) {
        this.statRepository = statRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return statRepository.count() > 0;
    }

    @Override
    public String readStatsFileContent() throws IOException {
        return Files.readString(Path.of(IMPORT_STAT_URL));
    }

    @Override
    public String importStats() throws JAXBException {
        StringBuilder sb = new StringBuilder();
        ValidationUtil validator = new ValidationUtilImpl();
        File file = new File(IMPORT_STAT_URL);
        JAXBContext context = JAXBContext.newInstance(ImportStatWrapper.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ImportStatWrapper wrapper = (ImportStatWrapper) unmarshaller.unmarshal(file);
        List<ImportStatDto> dtos = wrapper.getStats();
        for (ImportStatDto dto : dtos) {
            if (statRepository.findByPassingAndShootingAndEndurance(dto.getPassing(),dto.getShooting(),dto.getEndurance()) != null ||
            !validator.validate(dto)){
                sb.append(INVALID_STAT)
                        .append(System.lineSeparator());
                continue;
            }
            Stat stat = modelMapper.map(dto, Stat.class);
            statRepository.saveAndFlush(stat);
            sb.append(String.format(SUCCESSFULLY_IMPORTED_STAT,stat.getShooting(),stat.getPassing(),stat.getEndurance()))
                    .append(System.lineSeparator());
        }
        return sb.toString();
    }

    @Override
    public Stat getByPassingAndShootingAndEndurance(double passing, double shooting, double endurance) {
        return statRepository.findByPassingAndShootingAndEndurance(passing,shooting,endurance);
    }

    @Override
    public Stat getById(long id) {
        return statRepository.findById(id);
    }
}
