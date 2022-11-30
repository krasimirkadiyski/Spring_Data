package softuni.exam.config.constants;

import java.nio.file.Path;

public enum Paths {
    ;
    public final static Path IMPORT_COUNTRY_PATH = Path.of("src/main/resources/files/json/countries.json");
    public final static Path IMPORT_CITY_PATH = Path.of("src/main/resources/files/json/cities.json");
    public final static Path IMPORT_FORECAST_PATH = Path.of("src/main/resources/files/xml/forecasts.xml");
    public final static String FORECAST_URL = "src/main/resources/files/xml/forecasts.xml";

}
