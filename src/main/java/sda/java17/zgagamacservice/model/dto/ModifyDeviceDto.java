package sda.java17.zgagamacservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModifyDeviceDto {

    private Long idToModify;
    private String name;
    private Double value;
    private LocalDateTime dateAdded;
    private String serialNumber;
    private int productionYear;
}
