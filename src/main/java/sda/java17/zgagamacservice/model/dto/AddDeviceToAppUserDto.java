package sda.java17.zgagamacservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddDeviceToAppUserDto {

    // dopisac tu tez brakujace pola.
    @NotNull
    private Long userId;
    private String name;
    private Double value;
    private int productionYear;
    private String serialNumber;
}