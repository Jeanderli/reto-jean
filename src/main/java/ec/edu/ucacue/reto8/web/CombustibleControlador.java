/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ucacue.reto8.web;

import ec.edu.ucacue.reto8.domain.Combustible;
import ec.edu.ucacue.reto8.domain.Conductores;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import ec.edu.ucacue.reto8.servicio.CombustibleServicio;
import ec.edu.ucacue.reto8.servicio.ConductoresServicio;
import java.util.List;
/**
 *
 * @author Jorge Luis
 */

@Controller
@Slf4j
public class CombustibleControlador {
    
    @Autowired
    private CombustibleServicio combustibleServicio;
    
    @Autowired
    private ConductoresServicio conductoresServicio;

//    @GetMapping("/")
//    public String inicio(Model model, @AuthenticationPrincipal User user) {
//        log.info("ejecutando el controlador Spring MVC");
//        log.info("usuario que hizo login:" + user);
//        return "index";
//    }

    @GetMapping("/combustible")
    public String mostarCombustible(Model model) {
        var combustibles = combustibleServicio.listarCombustibles();
        model.addAttribute("combustibles", combustibles);
        return "combustible";
    }
    
    @GetMapping("/nuevoCombustible")
    public String agregar(Model model) {
        model.addAttribute("combustible", new Combustible());
        
        List<Conductores> conductores = conductoresServicio.listaConductores();
        model.addAttribute("conductores", conductores);
        
        return "nuevoCombustible";
    }

    @PostMapping("/guardarCombustible")
    public String guardar(@Valid @ModelAttribute("combustible") Combustible combustible,
                          BindingResult errores,
                          Model model) {
        if (errores.hasErrors()) {
            List<Conductores> conductores = conductoresServicio.listaConductores();
            model.addAttribute("conductores", conductores);
            return (combustible.getIdCombustible() != null) ? "modificarCombustible" : "nuevoCombustible";
        }
        combustibleServicio.guardar(combustible);
        return "redirect:/combustible";
    }

    @GetMapping("/editarCombustible/{idCombustible}")
    public String editar(@PathVariable("idCombustible") Long idCombustible, Model model) {
        Combustible combustible = new Combustible();
        combustible.setIdCombustible(idCombustible);
        System.out.println("Combustible: " + combustible.getIdCombustible());
        combustible = combustibleServicio.encontrarCombustible(combustible);
        model.addAttribute("combustible", combustible);
        
        List<Conductores> conductores = conductoresServicio.listaConductores();
        model.addAttribute("conductores", conductores);
        
        return "modificarCombustible";
    }

    @GetMapping("/eliminarCombustible/{idCombustible}")
    public String eliminar(@PathVariable("idCombustible") Long idCombustible, Model model) {
         Combustible combustible = new Combustible();
        combustible.setIdCombustible(idCombustible);
        combustibleServicio.eliminar(combustible);
        return "redirect:/combustible";
    }

}
