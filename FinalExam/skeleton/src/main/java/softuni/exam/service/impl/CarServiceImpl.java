package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportCarXmlDto;
import softuni.exam.models.dto.ImportCarXmlWrapper;
import softuni.exam.models.entity.Car;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.CarService;
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

import static softuni.exam.util.OutputMessages.INVALID_CAR;
import static softuni.exam.util.OutputMessages.SUCCESSFULLY_IMPORTED_CAR;
import static softuni.exam.util.Paths.CARS_XML;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ModelMapper mapper;

    public CarServiceImpl(CarRepository carRepository, ModelMapper mapper) {
        this.carRepository = carRepository;
        this.mapper = mapper;
    }

    @Override
    public boolean areImported() {
        return this.carRepository.count() > 0;
    }

    @Override
    public String readCarsFromFile() throws IOException {
        return Files.readString(Path.of(CARS_XML));
    }

    @Override
    public String importCars() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        ValidationUtil validator = new ValidationUtilImpl();
        File file = new File(CARS_XML);
        JAXBContext context = JAXBContext.newInstance(ImportCarXmlWrapper.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ImportCarXmlWrapper wrapper = (ImportCarXmlWrapper) unmarshaller.unmarshal(file);
        List<ImportCarXmlDto> dtos = wrapper.getCars();

        for (ImportCarXmlDto dto : dtos) {
            if (!validator.validate(dto) || isDuplicated(dto.getPlateNumber())){
                sb.append(INVALID_CAR)
                        .append(System.lineSeparator());
                continue;
            }
            Car car = mapper.map(dto, Car.class);
            this.carRepository.saveAndFlush(car);
            sb.append(String.format(SUCCESSFULLY_IMPORTED_CAR,car.getCarMake(),car.getCarModel()))
                    .append(System.lineSeparator());
        }
        return sb.toString();
    }

    @Override
    public Boolean isDuplicated(String plate) {
       return this.carRepository.findByPlateNumber(plate) != null;
    }

    @Override
    public Car getById(Long id) {
        return this.carRepository.findByIdIs(id);
    }

}
