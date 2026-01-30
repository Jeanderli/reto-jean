/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ucacue.reto8.web;

import ec.edu.ucacue.reto8.domain.Usuario;
import ec.edu.ucacue.reto8.servicio.UsuarioServicio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author DAVID
 */
@Controller
@Slf4j
public class Controlador {
    
    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/")
    public String inicio(Model model, @AuthenticationPrincipal User user) {
        log.info("ejecutando el controlador Spring MVC");
        log.info("usuario que hizo login:" + user);
        return "index";
    }

    @GetMapping("/registroUsuario")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registroUsuario"; // This must match the HTML file name
    }

    @PostMapping("/registroUsuario")
    public String registrarUsuario(@ModelAttribute Usuario usuario) {
        usuarioServicio.registrarUsuario(usuario);
        return "redirect:/login"; // Redirect after successful registration
    }
    
}
