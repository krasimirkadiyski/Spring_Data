package softuni.exam.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExportDto {
    private String cityName;
    private Double minTemperature;
    private Double maxTemperature;
    private LocalTime sunrise;
    private LocalTime sunset;

    @Override
    public String toString() {
      return String.format("""
              City: %s:
                 		-min temperature: %.2f
                 		--max temperature: %.2f
                 		---sunrise: %s
                        ----sunset: %s
                            
              """,cityName,minTemperature,maxTemperature,sunrise.toString(),sunset.toString());
    }
}

