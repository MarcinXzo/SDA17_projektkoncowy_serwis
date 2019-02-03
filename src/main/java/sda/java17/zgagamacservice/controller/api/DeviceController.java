package sda.java17.zgagamacservice.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sda.java17.zgagamacservice.model.Device;
import sda.java17.zgagamacservice.model.dto.AddDeviceToAppUserDto;
import sda.java17.zgagamacservice.model.dto.ModifyDeviceDto;
import sda.java17.zgagamacservice.service.DeviceService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/device/")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody AddDeviceToAppUserDto dto) {
        Optional<Device> optionalDevice = deviceService.addDevice(dto);
        if (optionalDevice.isPresent()) {
            return ResponseEntity.ok(optionalDevice.get());
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/modify")
    public ResponseEntity modify(@RequestBody ModifyDeviceDto dto) {
//        Optional<Device> optionalDevice = deviceService.modify(dto);
//        if (optionalDevice.isPresent()) {
//            return ResponseEntity.ok(optionalDevice.get());
//        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/list/{user_id}")
    public ResponseEntity listUserDevices(@PathVariable(name = "user_id") Long id) {
        List<Device> optionalDevice = deviceService.listUserDevices(id);
        return ResponseEntity.ok(optionalDevice);
    }

}
