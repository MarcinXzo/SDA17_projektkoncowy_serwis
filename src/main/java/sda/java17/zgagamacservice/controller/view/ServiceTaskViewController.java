package sda.java17.zgagamacservice.controller.view;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sda.java17.zgagamacservice.model.AppUser;
import sda.java17.zgagamacservice.model.Device;
import sda.java17.zgagamacservice.model.ServiceTask;
import sda.java17.zgagamacservice.model.dto.AddServiceTaskDto;
import sda.java17.zgagamacservice.service.LoginService;
import sda.java17.zgagamacservice.service.ServiceTaskService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/view/servicetask")
public class ServiceTaskViewController {

    @Autowired
    private ServiceTaskService serviceTaskService;

    @Autowired
    private LoginService loginService;

    @GetMapping("/list")
    public String getList(Model model) {
        // szukamy zalogowanego użytkownika
        Optional<AppUser> clientOptional = loginService.getLoggedInUser();
        // jeśli udało się go znaleźć
        if (clientOptional.isPresent()) {
            // listujemy jego wszystkie taski
            List<ServiceTask> serviceTaskList =
                    serviceTaskService.getAllUserServiceTasks(clientOptional.get().getId());

            // dodajemy taski do modelu
            model.addAttribute("serviceTasks", serviceTaskList);

            //zwracamy widok
            return "servicetask/list";
        }

        // jeśli nie jesteśmy zalogowani przekierowujemy na stronę logowania.
        return "redirect:/login";
    }

    @GetMapping("/add")
    public String getAddForm(Model model) {
        Optional<AppUser> loggedInClient = loginService.getLoggedInUser();
        if (loggedInClient.isPresent()) {
            AppUser appUser = loggedInClient.get();

            model.addAttribute("user_devices", appUser.getDeviceList());

            AddServiceTaskDto addServiceTask = new AddServiceTaskDto();
            model.addAttribute("created_task", addServiceTask);

            return "servicetask/add_form";
        }

        return "redirect:/login";
    }


    @GetMapping("/add/{device_id}")
    public String getAddForm(Model model, @PathVariable(name = "device_id") Long device_id) {
        Optional<AppUser> loggedInAppUser = loginService.getLoggedInUser();
        if (loggedInAppUser.isPresent()) {
            AppUser appUser = loggedInAppUser.get();

            Optional<Device> deviceOpt = appUser.getDeviceList().stream().filter(device -> device.getId() == device_id).findAny();
            if (deviceOpt.isPresent()) {
                AddServiceTaskDto addServiceTask = new AddServiceTaskDto();
                addServiceTask.setDeviceId(device_id);

                model.addAttribute("created_task", addServiceTask);
                return "servicetask/add_form";
            }
        }

        return "redirect:/login";
    }

    @PostMapping("/add")
    public String addTask(AddServiceTaskDto dto, Model model) {
        Optional<AppUser> loggedInAppUser = loginService.getLoggedInUser();
        if (loggedInAppUser.isPresent()) {
            AppUser appUser = loggedInAppUser.get();

            // upewnienie się w stylu 'dmucham na zimne'
            Optional<Device> deviceOptional = appUser.getDeviceList()
                    .stream()
                    .filter(device -> device.getId().equals(dto.getDeviceId()))
                    .findAny();

            // jeśli urządzenie zostało znalezione w urządzeniach tego użytkownika
            if (deviceOptional.isPresent()) {
                // dodajemy urządzenie do bazy
                serviceTaskService.add(dto);

                // przekierowujemy na listę zadań.
                return "redirect:/view/servicetask/list";
            }
        }

        return "redirect:/login";
    }

    @GetMapping("/delete/{id}")
    public String remove(Model model, @PathVariable (name = "id") Long id) {
        serviceTaskService.remove(id);

        return "redirect:/view/servicetask/list";// zwracam nazwę html
    }
}
