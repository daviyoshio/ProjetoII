package br.edu.ibmec.ProjetoIII.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UsuarioController {

    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {
        // lista fake só pra não dar erro
        List<Usuario> usuariosFake = List.of(
            new Usuario("Maria"),
            new Usuario("João"),
            new Usuario("Carlos")
        );
        model.addAttribute("usuarios", usuariosFake);
        return "usuarios";
    }

    public static class Usuario {
        private String nome;
        public Usuario(String nome) { this.nome = nome; }
        public String getNome() { return nome; }
    }
}
