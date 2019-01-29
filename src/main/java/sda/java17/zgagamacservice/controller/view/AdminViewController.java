package sda.java17.zgagamacservice.controller.view;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sda.java17.zgagamacservice.model.ServiceTask;
import sda.java17.zgagamacservice.service.ServiceTaskService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/servicetasks/")
public class AdminViewController {

    @Autowired
    private ServiceTaskService serviceTaskService;

    @GetMapping("/list")
    public String getListOfTasks(Model model) {
        List<ServiceTask> serviceTaskList = serviceTaskService.getAll();

        model.addAttribute("tasks", serviceTaskList);
        model.addAttribute("options", Arrays.asList("done", "undone", "none"));

        return "admin/servicetasks_list";
    }

    @PostMapping("/list")
    public String getListOfTasksFilter(Model model, @RequestParam(name = "status") String status) {
        List<ServiceTask> serviceTaskList = serviceTaskService.getAll();

        if (status.equals("done")) {
            model.addAttribute("tasks", serviceTaskList.stream()
                    .filter(task -> task.getDateResolved() != null)
                    .collect(Collectors.toList()));
        } else if (status.equals("undone")) {
            model.addAttribute("tasks", serviceTaskList.stream()
                    .filter(task -> task.getDateResolved() == null)
                    .collect(Collectors.toList()));
        } else {
            model.addAttribute("tasks", serviceTaskList);
        }

        model.addAttribute("options", Arrays.asList("done", "undone", "none"));

        return "admin/servicetasks_list";
    }
}
