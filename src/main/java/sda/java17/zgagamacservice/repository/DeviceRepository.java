package sda.java17.zgagamacservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import sda.java17.zgagamacservice.model.AppUser;
import sda.java17.zgagamacservice.model.Device;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    List<Device> findAllByOwner(AppUser appuser);
}
