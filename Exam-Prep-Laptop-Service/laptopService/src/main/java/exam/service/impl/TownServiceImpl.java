package exam.service.impl;


import exam.model.dtos.ImportTownDto;
import exam.model.dtos.ImportTownWrapper;
import exam.model.entities.Town;
import exam.repository.TownRepository;
import exam.service.TownService;
import exam.util.ValidChecker;
import exam.util.ValidCheckerImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static exam.util.Paths.IMPORT_TOWNS_PATH;
import static exam.util.outputMessages.INVALID_TOWN;
import static exam.util.outputMessages.SUCCESSFULLY_IMPORTED_TOWN;


@Service
public class TownServiceImpl implements TownService {
        private final TownRepository townRepository;
        private final ModelMapper mapper;


    public TownServiceImpl(TownRepository townRepository, ModelMapper mapper) {
        this.townRepository = townRepository;
        this.mapper = mapper;

    }

    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files.readString(Path.of(IMPORT_TOWNS_PATH));
    }

    @Override
    public String importTowns() throws JAXBException, FileNotFoundException {
        ValidChecker validator = new ValidCheckerImpl();
        File file = new File(IMPORT_TOWNS_PATH);
        StringBuilder sb = new StringBuilder();
        JAXBContext context = JAXBContext.newInstance(ImportTownWrapper.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ImportTownWrapper wrapper = (ImportTownWrapper) unmarshaller.unmarshal(file);
        List<ImportTownDto> dtos = wrapper.getTowns();
        for (ImportTownDto dto : dtos) {
            if (!validator.validate(dto)){
                sb.append(INVALID_TOWN)
                        .append(System.lineSeparator());
                continue;
            }
            Town town = mapper.map(dto, Town.class);
            townRepository.saveAndFlush(town);
            sb.append(String.format(SUCCESSFULLY_IMPORTED_TOWN,town.getName()))
                    .append(System.lineSeparator());
        }
        return sb.toString();
    }

    @Override
    public Town getByName(String name) {
        return this.townRepository.findByName(name);
    }
}
