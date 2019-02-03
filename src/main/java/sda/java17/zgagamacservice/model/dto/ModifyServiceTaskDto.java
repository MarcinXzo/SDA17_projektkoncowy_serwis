package sda.java17.zgagamacservice.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sda.java17.zgagamacservice.model.Device;
import sda.java17.zgagamacservice.model.ServiceTask;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModifyServiceTaskDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idToModify;
    private LocalDateTime dateAdded;
    private LocalDateTime dateResolved;
    private LocalDateTime dateReopened;
    private String description;
    private String comment;

    @ManyToOne
    @JsonIgnore
    private ServiceTask serviceTask;
}
