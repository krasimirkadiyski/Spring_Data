package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportCityDto;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CityRepository;
import softuni.exam.service.CityService;
import softuni.exam.service.CountryService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.ValidationUtilImpl;

import java.io.IOException;
import java.nio.file.Files;

import static softuni.exam.config.constants.Paths.IMPORT_CITY_PATH;
import static softuni.exam.config.constants.outMessages.INVALID_CITY;
import static softuni.exam.config.constants.outMessages.SUCCESSFULLY_IMPORTED_CITY;

@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final CountryService countryService;
    private final ModelMapper mapper;
    private final Gson gson;

    public CityServiceImpl(CityRepository cityRepository, CountryService countryService, ModelMapper mapper, Gson gson) {
        this.cityRepository = cityRepository;
        this.countryService = countryService;
        this.mapper = mapper;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return cityRepository.count() > 0;
    }

    @Override
    public String readCitiesFileContent() throws IOException {
        return Files.readString(IMPORT_CITY_PATH);
    }

    @Override
    public String importCities() throws IOException {
        final StringBuilder sb = new StringBuilder();
        ValidationUtil validator = new ValidationUtilImpl();
        ImportCityDto[] dtos = gson.fromJson(readCitiesFileContent(), ImportCityDto[].class);
        for (ImportCityDto dto : dtos) {
            if (!validator.validate(dto) || isDuplicated(dto.getCityName())){
                sb.append(INVALID_CITY)
                        .append(System.lineSeparator());
                continue;
            }
            City city = mapper.map(dto, City.class);
            Country country = countryService.getById(dto.getCountry());
            city.setCountry(country);
            cityRepository.saveAndFlush(city);
            sb.append(String.format(SUCCESSFULLY_IMPORTED_CITY,city.getCityName(),city.getPopulation()))
                    .append(System.lineSeparator());

        }
        return sb.toString();
    }

    @Override
    public Boolean isDuplicated(String name) {
        return cityRepository.findByCityName(name) != null;
    }

    @Override
    public City getByName(String name) {
        return cityRepository.findByCityName(name);
    }

    @Override
    public City getById(long id) {
        return cityRepository.findByIdIs(id);
    }
}
