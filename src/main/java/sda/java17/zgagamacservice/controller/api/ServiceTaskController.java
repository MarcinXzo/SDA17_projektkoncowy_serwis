package sda.java17.zgagamacservice.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sda.java17.zgagamacservice.model.ServiceTask;
import sda.java17.zgagamacservice.model.dto.AddServiceTaskDto;
import sda.java17.zgagamacservice.service.ServiceTaskService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/servicetask/")
public class ServiceTaskController {
    @Autowired
    private ServiceTaskService serviceTaskService;

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody AddServiceTaskDto dto) {
        Optional<ServiceTask> serviceTaskOptional = serviceTaskService.add(dto);
        if (serviceTaskOptional.isPresent()) {
            return ResponseEntity.ok(serviceTaskOptional.get());
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/listUserServiceTasks/{user_id}")
    public ResponseEntity getUserServiceTasks(@PathVariable(name = "user_id") Long user_id) {
        List<ServiceTask> tasks = serviceTaskService.getAllUserServiceTasks(user_id);

        return ResponseEntity.ok(tasks);
    }

    @RequestMapping(value = "/removeServiceTask", method = RequestMethod.GET)
    public String removeServiceTask(@RequestParam(name = "serviceTaskToRemoveId") Long id) {
        serviceTaskService.remove(id);
//    return "admin/userlist";
        return "redirect:/servicetask/list";
    }

}
