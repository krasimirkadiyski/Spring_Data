package bg.softuni.springdataautomappingobjects.entities_exe1.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO_01 {
    private String firstName;
    private String lastName;
    private BigDecimal salary;
    private String city;
    private String street;
}
