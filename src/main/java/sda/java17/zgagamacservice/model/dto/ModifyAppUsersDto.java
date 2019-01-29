package sda.java17.zgagamacservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModifyAppUsersDto {
    private Long idToModify;

    @Email
    private String email;

    @Size(min = 3, max = 255)
    private String name;

    @Size(min = 3, max = 255)
    private String surname;

    @Size(min = 5, max = 255)
    private String password;
}
