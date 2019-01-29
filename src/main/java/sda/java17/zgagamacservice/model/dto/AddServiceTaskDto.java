package sda.java17.zgagamacservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddServiceTaskDto {
    private Long deviceId;
    private String description;
}
