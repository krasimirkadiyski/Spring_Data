package softuni.exam.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExportDto {
    private String carMake;
    private String carModel;
    private int carKilometers;
    private String mechanicFirstName;
    private String mechanicLastName;
    private Long taskId;
    private Double carEngine;
    private Double taskPrice;

    @Override
    public String toString() {
        return String.format("""
                Car %s %s with %dkm
                -Mechanic: %s %s - task №%d:
                --Engine: %.1f
                ---Price: %.2f$
                """,carMake,carModel,carKilometers,mechanicFirstName,mechanicLastName,taskId,carEngine,taskPrice);
    }
}
