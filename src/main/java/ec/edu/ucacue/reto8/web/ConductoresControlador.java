package ec.edu.ucacue.reto8.web;


import ec.edu.ucacue.reto8.domain.Combustible;
import ec.edu.ucacue.reto8.domain.Conductores;
import ec.edu.ucacue.reto8.servicio.CombustibleServicio;
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


@Controller
@Slf4j

public class ConductoresControlador {
    @Autowired
    ConductoresServicio conductoresServicio;
     
    @GetMapping("/conductores")
    public String MostarConductores(Model model) {
       var conductores=conductoresServicio.listaConductores();
       model.addAttribute("conductores",conductores); 
       return "conductores";
    }
    @GetMapping("/nuevoConductores")
    public String agregar(Model model) {
        model.addAttribute("conductor", new Conductores());
        return "nuevoConductores";
    }
    @PostMapping("/guardarConductores")
    public String guardar(@Valid @ModelAttribute("conductor") Conductores conductor,
                          BindingResult errores,
                          Model model) {
        if (errores.hasErrors()) {
            // Si viene con ID es edición; si no, es nuevo
            return (conductor.getIdConductores() != 0) ? "modificarConductores" : "nuevoConductores";
        }
        conductoresServicio.guardar(conductor);
        return "redirect:/conductores";
    }
     @GetMapping("/editarConductores/{idConductores}")
    public String editar(@PathVariable("idConductores") Long idConductores, Model model) {
       Conductores conductor = new Conductores();
        conductor.setIdConductores(idConductores);
        System.out.println("Conductor: " + conductor.getIdConductores());
        conductor = conductoresServicio.encontrarConductores(conductor);
        model.addAttribute("conductor", conductor);
        return "modificarConductores";
    }
    
    @GetMapping("/eliminarConductores/{idConductores}")
    public String eliminar(@PathVariable("idConductores") Long idConductores, Model model) {
         Conductores conductor = new Conductores();
        conductor.setIdConductores(idConductores);
        conductoresServicio.eliminar(conductor);
        return "redirect:/conductores";
    }

}
