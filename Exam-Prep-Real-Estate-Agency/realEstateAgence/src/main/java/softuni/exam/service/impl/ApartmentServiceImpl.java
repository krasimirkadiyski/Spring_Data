package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.xmlDto.ImportApartmentDto;
import softuni.exam.models.dto.xmlDto.ImportApartmentWrapper;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.repository.entity.Apartment;
import softuni.exam.repository.entity.Town;
import softuni.exam.service.ApartmentService;
import softuni.exam.service.TownService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.ValidationUtilImpl;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class ApartmentServiceImpl implements ApartmentService {
    private final ApartmentRepository apartmentRepository;
    private final TownService townService;
    private final ModelMapper modelMapper;


    public ApartmentServiceImpl(ApartmentRepository apartmentRepository, TownService townService, ModelMapper modelMapper) {
        this.apartmentRepository = apartmentRepository;
        this.townService = townService;
        this.modelMapper = modelMapper;
    }

    @Override
    public Apartment getById(Long id) {
      return apartmentRepository.findByIdIs(id);
    }

    @Override
    public boolean areImported() {
        return apartmentRepository.count() > 0;
    }

    @Override
    public String readApartmentsFromFile() throws IOException {
        return Files.readString(Path.of("src/main/resources/files/xml/apartments.xml"));
    }

    @Override
    public String importApartments() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        File file = new File("src/main/resources/files/xml/apartments.xml");
        ValidationUtil validator = new ValidationUtilImpl();
        JAXBContext context = JAXBContext.newInstance(ImportApartmentWrapper.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ImportApartmentWrapper wrapper = (ImportApartmentWrapper) unmarshaller.unmarshal(file);
        List<ImportApartmentDto> dtos = wrapper.getImportApartmentDto();
        for (ImportApartmentDto dto : dtos) {
            if (validator.validate(dto)){
                Apartment apartment = modelMapper.map(dto, Apartment.class);
                Town town = townService.getByName(dto.getTown());
                apartment.setTown(town);
                apartmentRepository.saveAndFlush(apartment);
                sb.append(String.format("Successfully imported apartment %s - %.2f",dto.getApartmentType(),dto.getArea()))
                        .append(System.lineSeparator());

            }else{
                sb.append("Invalid apartment")
                        .append(System.lineSeparator());
            }
        }

        return sb.toString();
    }
}
