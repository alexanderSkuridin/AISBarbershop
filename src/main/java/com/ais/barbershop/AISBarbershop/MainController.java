package com.ais.barbershop.AISBarbershop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/")
public class MainController {
    @RequestMapping({ "/", "/index" })
    public String index() {
        return "services";
    }
}
