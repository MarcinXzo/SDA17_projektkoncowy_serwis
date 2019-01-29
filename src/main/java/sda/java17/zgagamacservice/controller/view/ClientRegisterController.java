package sda.java17.zgagamacservice.controller.view;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import sda.java17.zgagamacservice.model.AppUser;
import sda.java17.zgagamacservice.model.dto.AddAppUserDto;
import sda.java17.zgagamacservice.service.AppUserService;
import sda.java17.zgagamacservice.service.LoginService;

import java.util.Optional;

@Controller
public class ClientRegisterController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public String getLoginView() {
        return "appuser/login_form";
    }

    @GetMapping("/")
    public String getIndex(Model model) {
        Optional<AppUser> appUserOptional = loginService.getLoggedInUser();
        if (appUserOptional.isPresent()) {
            AppUser appUser = appUserOptional.get();

            model.addAttribute("username", appUser.getEmail());
        }

        return "index";
    }

    @GetMapping("/register")
    public String getAddView(Model model) {
        // wysyłam pusty obiekt do formularza
        model.addAttribute("added_object", new AddAppUserDto());
        return "appuser/register_form";
    }

    @PostMapping("/register")
    public String getAddView(Model model, AddAppUserDto dto) {
        // Otrzymuję wypełniony obiekt z formularza który załadował mapping wyżej

        // tutaj dokonam walidacji i dodania user'a
        if (dto.getPassword().isEmpty() || dto.getPassword().length() < 5) {
            model.addAttribute("added_object", dto);
            model.addAttribute("error_message", "Too simple password.");
            return "appuser/register_form";
        }

        // udało się, rejestrujemy!
        // wewnątrz metody register powinna być walidacja nie istniejącego maila
        Optional<AppUser> appUserOptional = appUserService.register(dto);
        if (!appUserOptional.isPresent()) {
            model.addAttribute("added_object", dto);
            model.addAttribute("error_message", "User with that email already exists.");
            return "appuser/register_form";
        }

        // przekierujemy na inną stronę
        // przekierowanie na stronę '/'
        return "redirect:/";
    }
}
