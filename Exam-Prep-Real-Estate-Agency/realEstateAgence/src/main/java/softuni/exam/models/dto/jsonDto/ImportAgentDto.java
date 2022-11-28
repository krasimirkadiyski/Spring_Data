package softuni.exam.models.dto.jsonDto;

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
public class ImportAgentDto {

    @NotNull
    @Size(min = 2)
    private String firstName;
    @NotNull
    @Size(min = 2)
    private String lastName;
    private String town;
    @Email
    @NotNull
    private String email;
}
