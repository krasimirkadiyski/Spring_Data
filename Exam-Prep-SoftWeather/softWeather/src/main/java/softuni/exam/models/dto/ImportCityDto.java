package softuni.exam.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImportCityDto {
    @NotNull
    @Size(min = 2, max = 60)
    private String cityName;
    @NotNull
    @Size(min = 2)
    private String description;
    @NotNull
    @Min(500)
    private Long population;
    private Long country;
}
