package sda.java17.zgagamacservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Double value;
    private LocalDateTime dateAdded;
    private String serialNumber;
    private int productionYear;
// dopisac funkcjonalnosc

    @ManyToOne
    @JsonIgnore
    private AppUser owner;

    @OneToMany(mappedBy = "device")
    @JsonIgnore
    private List<ServiceTask> serviceTasks;

}
