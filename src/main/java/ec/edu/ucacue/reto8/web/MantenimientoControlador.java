/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ucacue.reto8.web;

import ec.edu.ucacue.reto8.domain.Conductores;
import ec.edu.ucacue.reto8.domain.Mantenimiento;
import ec.edu.ucacue.reto8.servicio.ConductoresServicio;
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
import ec.edu.ucacue.reto8.servicio.MantenimientoServicio;
import java.util.List;
/**
 *
 * @author Jorge Luis
 */

@Controller
@Slf4j
public class MantenimientoControlador {
    
    @Autowired
    private MantenimientoServicio mantenimientoServicio;
    
    @Autowired
    private ConductoresServicio conductoresServicio;
    
    @GetMapping("/mantenimiento")
    public String mostarMantenimiento(Model model) {
        var mantenimientos = mantenimientoServicio.listarMantenimiento();
        model.addAttribute("mantenimientos", mantenimientos);
        return "mantenimiento";
    }
    
    @GetMapping("/nuevoMantenimiento")
    public String agregar(Model model) {
        model.addAttribute("mantenimiento", new Mantenimiento());
        List<Conductores> conductores = conductoresServicio.listaConductores();
        model.addAttribute("conductores", conductores);
        return "nuevoMantenimiento";
    }

    @PostMapping("/guardarMantenimiento")
    public String guardar(@Valid @ModelAttribute("mantenimiento") Mantenimiento mantenimiento,
                          BindingResult errores,
                          Model model) {
        if (errores.hasErrors()) {
            List<Conductores> conductores = conductoresServicio.listaConductores();
            model.addAttribute("conductores", conductores);
            return (mantenimiento.getIdMantenimiento() != 0) ? "modificarMantenimiento" : "nuevoMantenimiento";
        }
        mantenimientoServicio.guardar(mantenimiento);
        return "redirect:/mantenimiento";
    }

    @GetMapping("/editarMantenimiento/{idMantenimiento}")
    public String editar(@PathVariable("idMantenimiento") Long idMantenimiento, Model model) {
        Mantenimiento mantenimiento = new Mantenimiento();
        mantenimiento.setIdMantenimiento(idMantenimiento);
        System.out.println("Mantenimiento: " + mantenimiento.getIdMantenimiento());
        mantenimiento = mantenimientoServicio.encontrarMantenimiento(mantenimiento);
        model.addAttribute("mantenimiento", mantenimiento);
        List<Conductores> conductores = conductoresServicio.listaConductores();
        model.addAttribute("conductores", conductores);
        return "modificarMantenimiento";
    }

    @GetMapping("/eliminarMantenimiento/{idMantenimiento}")
    public String eliminar(@PathVariable("idMantenimiento") Long idMantenimiento, Model model) {
         Mantenimiento mantenimiento = new Mantenimiento();
        mantenimiento.setIdMantenimiento(idMantenimiento);
        mantenimientoServicio.eliminar(mantenimiento);
        return "redirect:/mantenimiento";
    }

}