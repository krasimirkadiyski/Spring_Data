package softuni.exam.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImportPartsDto {
     @NotNull
     @Size(min = 2, max = 19)
     private String partName;
     @NotNull
     @DecimalMin("10")
     @DecimalMax("2000")
     private Double price;
     @NotNull
     @Min(1)
     private Integer quantity;
}
