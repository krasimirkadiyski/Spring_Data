package exam.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImportCustomerDto {
    @Size(min = 2)
    @NotNull
    private String firstName;
    @Size(min = 2)
    @NotNull
    private String lastName;
    @Email
    @NotNull
    private String email;
    @NotNull
    private String registeredOn;
    private TownJsonDto town;
}
