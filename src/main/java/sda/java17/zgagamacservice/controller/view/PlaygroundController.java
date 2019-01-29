package sda.java17.zgagamacservice.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/play")
public class PlaygroundController {

    @GetMapping("/multiply")
    public String multiply() {
        // stwórz stronę pod adresem /play/multiply w której dodaj formularz
        // tabliczki mnożenia (2 liczby). Po wysłaniu danych wyświetl
        // tabliczkę mnożenia pod adresem /play/multiply (metoda POST)
        return "playground/multiply";
    }

    @PostMapping("/multiply")
    public String multiplyPost(Model model,
                               @RequestParam(name = "x") int x,
                               @RequestParam(name = "y") int y) {
        model.addAttribute("size_x", x);
        model.addAttribute("size_y", y);

        return "playground/multiply";
    }
}
