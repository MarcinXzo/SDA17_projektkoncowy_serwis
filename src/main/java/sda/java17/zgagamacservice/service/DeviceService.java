package sda.java17.zgagamacservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sda.java17.zgagamacservice.model.AppUser;
import sda.java17.zgagamacservice.model.Device;
import sda.java17.zgagamacservice.model.dto.AddDeviceToAppUserDto;
import sda.java17.zgagamacservice.model.dto.ModifyDeviceDto;
import sda.java17.zgagamacservice.repository.AppUserRepository;
import sda.java17.zgagamacservice.repository.DeviceRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    public Optional<Device> addDevice(AddDeviceToAppUserDto dto) {
        Optional<AppUser> appUserOptional = appUserRepository.findById(dto.getUserId());
        if (appUserOptional.isPresent()) {
            AppUser appuser = appUserOptional.get();

            Device device = new Device();
            device.setDateAdded(LocalDateTime.now());
            device.setName(dto.getName());
            device.setValue(dto.getValue());

            device.setOwner(appuser);
            device = deviceRepository.save(device);

            appuser.getDeviceList().add(device);
            appuser = appUserRepository.save(appuser);

            return Optional.of(device);
        }

        return Optional.empty();
    }

    public List<Device> listUserDevices(Long id) {
        Optional<AppUser> appUserOptional = appUserRepository.findById(id);
        if (appUserOptional.isPresent()) {
            AppUser appUser = appUserOptional.get();

            List<Device> devices = deviceRepository.findAllByOwner(appUser);

            return devices;
        }
        return new ArrayList<>();
    }

    public void remove(Long id) {
        deviceRepository.deleteById(id);
    }

    public Optional<Device> modify(ModifyDeviceDto dto) {
        return Optional.empty();
    }
}
