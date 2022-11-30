package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportCountryDto;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CountryService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.ValidationUtilImpl;

import java.io.IOException;
import java.nio.file.Files;

import static softuni.exam.config.constants.Paths.IMPORT_COUNTRY_PATH;
import static softuni.exam.config.constants.outMessages.INVALID_COUNTRY;
import static softuni.exam.config.constants.outMessages.SUCCESSFULLY_IMPORTED_COUNTRY;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    private final ModelMapper mapper;
    private final Gson gson;

    public CountryServiceImpl(CountryRepository countryRepository, ModelMapper mapper, Gson gson) {
        this.countryRepository = countryRepository;
        this.mapper = mapper;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return countryRepository.count() > 0;
    }

    @Override
    public String readCountriesFromFile() throws IOException {
        return Files.readString(IMPORT_COUNTRY_PATH);
    }

    @Override
    public String importCountries() throws IOException {
        ValidationUtil validator = new ValidationUtilImpl();
        final StringBuilder sb = new StringBuilder();

        ImportCountryDto[] dtos = gson.fromJson(readCountriesFromFile(), ImportCountryDto[].class);


        for (ImportCountryDto dto : dtos) {

                if (!validator.validate(dto) || isDuplicated(dto.getCountryName())){
                    sb.append(INVALID_COUNTRY)
                            .append(System.lineSeparator());
                    continue;
                }
            Country country = mapper.map(dto, Country.class);
                countryRepository.saveAndFlush(country);
                sb.append(String.format(SUCCESSFULLY_IMPORTED_COUNTRY,country.getCountryName(),country.getCurrency()))
                        .append(System.lineSeparator());
        }
        return sb.toString();
    }

    @Override
    public Boolean isDuplicated(String name) {
        return countryRepository.findByCountryName(name) != null;
    }

    @Override
    public Country getById(Long id) {
        return countryRepository.findByIdIs(id);
    }
}
