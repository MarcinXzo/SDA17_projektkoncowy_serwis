package sda.java17.zgagamacservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceTask {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime dateAdded;
    private LocalDateTime dateResolved;
    private LocalDateTime dateReopened;
    private String description;
    private String comment;

    @ManyToOne
    @JsonIgnore
    private Device device;
}
