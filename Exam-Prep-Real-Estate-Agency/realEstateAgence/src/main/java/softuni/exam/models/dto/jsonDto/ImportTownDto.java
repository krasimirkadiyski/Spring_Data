package softuni.exam.models.dto.jsonDto;

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
public class ImportTownDto {
    @NotNull
    @Size(min = 2)
    private String townName;
    @NotNull
    @Min(1)
    private Long population;
}
