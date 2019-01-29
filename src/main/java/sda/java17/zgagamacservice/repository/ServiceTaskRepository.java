package sda.java17.zgagamacservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import sda.java17.zgagamacservice.model.Device;
import sda.java17.zgagamacservice.model.ServiceTask;

import java.util.List;

public interface ServiceTaskRepository extends JpaRepository<ServiceTask, Long> {
    List<ServiceTask> findAllByDeviceIn(List<Device> devices);
}
