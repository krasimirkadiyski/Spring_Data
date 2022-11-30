package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ExportDto;
import softuni.exam.models.dto.ImportForecastDto;
import softuni.exam.models.dto.ImportForecastWrapper;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.Forecast;
import softuni.exam.repository.ForecastRepository;
import softuni.exam.service.CityService;
import softuni.exam.service.ForecastService;
import softuni.exam.util.DOW;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.ValidationUtilImpl;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static softuni.exam.config.constants.Paths.FORECAST_URL;
import static softuni.exam.config.constants.Paths.IMPORT_FORECAST_PATH;
import static softuni.exam.config.constants.outMessages.INVALID_FORECAST;
import static softuni.exam.config.constants.outMessages.SUCCESSFULLY_IMPORTED_FORECAST;

@Service
public class ForecastServiceImpl implements ForecastService {
    private final ForecastRepository forecastRepository;
    private final ModelMapper mapper;
    private final CityService cityService;


    public ForecastServiceImpl(ForecastRepository forecastRepository, ModelMapper mapper, CityService cityService) {
        this.forecastRepository = forecastRepository;
        this.mapper = mapper;
        this.cityService = cityService;
    }

    @Override
    public boolean areImported() {
        return forecastRepository.count() > 0;
    }

    @Override
    public String readForecastsFromFile() throws IOException {
        return Files.readString(IMPORT_FORECAST_PATH);
    }

    @Override
    public String importForecasts() throws IOException, JAXBException {
        ValidationUtil validator = new ValidationUtilImpl();
        File file = new File(FORECAST_URL);
        JAXBContext context = JAXBContext.newInstance(ImportForecastWrapper.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        StringBuilder sb = new StringBuilder();
        ImportForecastWrapper wrapper = (ImportForecastWrapper) unmarshaller.unmarshal(file);
        List<ImportForecastDto> dtos = wrapper.getForecasts();
        for (ImportForecastDto dto : dtos) {
            if (!validator.validate(dto) || isDuplicated(dto.getCity(),dto.getDayOfWeek())){
                sb.append(INVALID_FORECAST)
                        .append(System.lineSeparator());
                continue;
            }
            Forecast forecast = mapper.map(dto, Forecast.class);
            City city = cityService.getById(dto.getCity());
            forecast.setCity(city);
            forecastRepository.saveAndFlush(forecast);
            sb.append(String.format(SUCCESSFULLY_IMPORTED_FORECAST,forecast.getDayOfWeek(),forecast.getMaxTemperature()))
                    .append(System.lineSeparator());

        }
        return sb.toString();
    }

    @Override
    public String exportForecasts() {
        StringBuilder sb = new StringBuilder();
        Set<Forecast> forecasts = forecastRepository.sundayForecast();
        LinkedHashSet<ExportDto> dtos = forecasts.stream().map(f -> mapper.map(f, ExportDto.class))
                .collect(Collectors.toCollection(LinkedHashSet::new));
        dtos.forEach(d -> sb.append(d.toString()));
        return sb.toString();
    }

    @Override
    public Boolean isDuplicated(long cityId, DOW dow) {
        return forecastRepository.findByCityAndDay(cityId,dow) != null;
    }
}
