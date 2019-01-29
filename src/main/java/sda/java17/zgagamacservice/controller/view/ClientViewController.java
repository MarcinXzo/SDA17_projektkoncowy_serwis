package sda.java17.zgagamacservice.controller.view;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sda.java17.zgagamacservice.model.AppUser;
import sda.java17.zgagamacservice.model.Device;
import sda.java17.zgagamacservice.service.AppUserService;
import sda.java17.zgagamacservice.service.LoginService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/view/client/")
public class ClientViewController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private LoginService loginService;

//    private int licznik = 0;
//    @GetMapping("/test")
    // wartość zwracana to nazwa pliku bez rozszerzenia html w katalogu templates
//    public String getAddView(Model model) {
    // model możemy przekazać w parametrze, ale jest opcjonalny
//        model.addAttribute("licznik", licznik);
    // attribute to to samo co attribute w request (JSP)
//        return "index";
//    }


    @GetMapping("/profile")
    // model - do wysłania danych do html
    public String viewProfile(Model model) {
        Optional<AppUser> optionalAppUser = loginService.getLoggedInUser();

        if (optionalAppUser.isPresent()) {
            AppUser appUser = optionalAppUser.get();
            List<Device> deviceList = appUser.getDeviceList();

            // ładuję klienta i jego urządzenia do modelu (jako atrybuty)
            // żeby je za chwile wyświetlić
            model.addAttribute("appuser", appUser);
            model.addAttribute("deviceList", deviceList);

            // widok może się załadować
            return "appuser/profile";
        }
        // po stronie backend'u - wykorzystaj dostępne Ci serwisy i
        // wyświetl na stronie html:
        // *- dane użytkownika o podanym id (z obiektu Client)
        // - wszystkie urządzenia tego użytkownika (lista Device)

        return "redirect:/login";
    }
}
