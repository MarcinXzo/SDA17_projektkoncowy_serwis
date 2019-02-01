package sda.java17.zgagamacservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sda.java17.zgagamacservice.model.AppUser;
import sda.java17.zgagamacservice.model.Device;
import sda.java17.zgagamacservice.model.ServiceTask;
import sda.java17.zgagamacservice.model.dto.AddServiceTaskDto;
import sda.java17.zgagamacservice.repository.AppUserRepository;
import sda.java17.zgagamacservice.repository.DeviceRepository;
import sda.java17.zgagamacservice.repository.ServiceTaskRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceTaskService {
    @Autowired
    private ServiceTaskRepository serviceTaskRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    public Optional<ServiceTask> add(AddServiceTaskDto dto) {
        Optional<Device> deviceOptional = deviceRepository.findById(dto.getDeviceId());
        if (deviceOptional.isPresent()) {
            Device device = deviceOptional.get();

            ServiceTask serviceTask = new ServiceTask();
            serviceTask.setDateAdded(LocalDateTime.now());
            serviceTask.setDescription(dto.getDescription());
            serviceTask.setDevice(device);

            serviceTask = serviceTaskRepository.save(serviceTask);
            device.getServiceTasks().add(serviceTask);

            device = deviceRepository.save(device);
            return Optional.of(serviceTask);
        }
        return Optional.empty();
    }

    public List<ServiceTask> getAllUserServiceTasks(Long user_id) {
        Optional<AppUser> appUserOptional = appUserRepository.findById(user_id);
        if (appUserOptional.isPresent()) {
            AppUser appUser = appUserOptional.get();
            List<Device> deviceList = deviceRepository.findAllByOwner(appUser);

            return serviceTaskRepository.findAllByDeviceIn(deviceList);
        }
        return new ArrayList<>();
    }

    public List<ServiceTask> getAll() {

        return serviceTaskRepository.findAll();
    }

    public void remove(Long id) {
           serviceTaskRepository.deleteById(id);

    }

    public void modify(Long id) {
        serviceTaskRepository.getOne(id);

    }
}
