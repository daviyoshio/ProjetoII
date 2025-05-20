package br.edu.ibmec.ProjetoIII.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String redirecionarParaUsuarios() {
        return "redirect:/usuarios";
    }
}

