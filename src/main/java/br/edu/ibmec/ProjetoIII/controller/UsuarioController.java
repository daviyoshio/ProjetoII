package br.edu.ibmec.ProjetoIII.controller;

import br.edu.ibmec.ProjetoIII.model.Usuario;
import br.edu.ibmec.ProjetoIII.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "usuarios";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();
        model.addAttribute("usuario", usuario);
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "usuarios";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
        return "redirect:/usuarios";
    }

    @PostMapping
    public String salvar(@RequestParam String nome,
                         @RequestParam String email,
                         @RequestParam(required = false) MultipartFile foto,
                         @RequestParam(required = false) Long id) throws IOException {

        Usuario usuario = (id != null) ? usuarioRepository.findById(id).orElse(new Usuario()) : new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);

        // salvar imagem em /static/uploads
        if (foto != null && !foto.isEmpty()) {
            String nomeArquivo = UUID.randomUUID() + "_" + foto.getOriginalFilename();
            Path caminho = Paths.get("src/main/resources/static/uploads", nomeArquivo);
            Files.createDirectories(caminho.getParent());
            Files.write(caminho, foto.getBytes());
            usuario.setCaminhoFoto("/uploads/" + nomeArquivo);
        }

        usuarioRepository.save(usuario);
        return "redirect:/usuarios";
    }
}
