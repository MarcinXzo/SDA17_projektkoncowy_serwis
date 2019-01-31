package sda.java17.zgagamacservice.controller.view;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sda.java17.zgagamacservice.model.AppUser;
import sda.java17.zgagamacservice.model.Device;
import sda.java17.zgagamacservice.model.dto.AddDeviceToAppUserDto;
import sda.java17.zgagamacservice.service.DeviceService;
import sda.java17.zgagamacservice.service.LoginService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/view/device/") //kolejnr mappingi skladaja sie w cala sciezke
public class DeviceViewController {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private LoginService loginService;

    @GetMapping("/add")
    public String add(Model model) {
        Optional<AppUser> clientOptional = loginService.getLoggedInUser();
        if (!clientOptional.isPresent()) {
            // jeśli nie jesteśmy zalogowani przekieruj na panel logowania
            return "redirect:/login";
        }

        // jeśli jesteśmy logowani stworzymy sobie obiekt logowania
        AddDeviceToAppUserDto addDeviceToClientDto = new AddDeviceToAppUserDto();
        // w obiekcie przypisujemy do pola userId nasz identyfikator zalogowanego użytkownika
        addDeviceToClientDto.setUserId(clientOptional.get().getId());

        // przekazujemy obiekt do formularza (żeby go za chwilę wypełnić)
        model.addAttribute("added_device", addDeviceToClientDto);

        return "device/add_form";// zwracam nazwę html
    }

    @PostMapping("/add")
    public String add(Model model, AddDeviceToAppUserDto dto) {
        // weryfikacja że ID w dto jest takie samo jak Id zalogowanego użytkownika.
        // (zalecane) możecie stworzyć metodę w LoginService
        // todo: metoda w loginService (checkLoggedInId(Long user_id))
        // w wyniku zwraca boolean - czy obecnie zalogowany użytkownik ma id == user_id(z parametru)
        // jeśli zalogowany użytkownik ma poprawne id, to przechodzimy dalej.
        // w przeciwnym razie przekieruj na stronę logowania.

        Optional<Device> optionalDevice = deviceService.addDevice(dto);
        if (!optionalDevice.isPresent()) {
            model.addAttribute("added_device", dto);
            model.addAttribute("error_message", "Validation error!");
            return "device/add_form";// zwracam nazwę html
        }

        // jeśli uda się zalogować wyświetl (przekieruj na) profil użytkownika
        return "redirect:/view/appuser/profile";
    }

    @GetMapping("/list")
    public String list(Model model) {
        Optional<AppUser> clientOptional = loginService.getLoggedInUser();
        if (!clientOptional.isPresent()) {
            // jeśli nie jesteśmy zalogowani przekieruj na panel logowania
            return "redirect:/login";
        }

        List<Device> deviceList = clientOptional.get().getDeviceList();
        // przekazujemy obiekt do formularza (żeby go za chwilę wypełnić)
        model.addAttribute("devices", deviceList);

        return "device/list";// zwracam nazwę html
    }
}
