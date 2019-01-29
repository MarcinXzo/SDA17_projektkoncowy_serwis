package sda.java17.zgagamacservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilterAppUsersDto {
    private String email;
    private String surname;
    private String name;
}
