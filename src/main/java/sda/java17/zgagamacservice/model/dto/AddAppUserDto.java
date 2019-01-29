package sda.java17.zgagamacservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddAppUserDto {
    @Email
    @NotEmpty
    private String email;

    @NotNull
    @Size(min = 3, max = 255)
    private String name;

    @NotEmpty
    @Size(min = 3, max = 255)
    private String surname;

    @NotEmpty
    @Size(min = 5, max = 255)
    private String password;
}
