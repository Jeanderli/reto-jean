/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ec.edu.ucacue.reto8.web;
import ec.edu.ucacue.reto8.domain.Conductores;
import ec.edu.ucacue.reto8.domain.Informacion;
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
import ec.edu.ucacue.reto8.servicio.InformacionServicio;
import java.util.List;
/**
 *
 * @author jeand
 */

@Controller
@Slf4j
public class InformacionControlador {

 @Autowired
    private InformacionServicio informacionServicio;
 
 @Autowired
    private ConductoresServicio conductoresServicio;

    @GetMapping("/informacion")
    public String mostarInformacion(Model model) {
        var informacion = informacionServicio.listarInformacion();
        model.addAttribute("informacion", informacion);
        return "informacion";
    }
    
    @GetMapping("/nuevoInformacion")
    public String agregar(Model model) {
        model.addAttribute("informacion", new Informacion());
        List<Conductores> conductores = conductoresServicio.listaConductores();
        model.addAttribute("conductores", conductores);
        return "nuevoInformacion";
    }

    @PostMapping("/guardarInformacion")
    public String guardar(@Valid @ModelAttribute("informacion") Informacion informacion,
                          BindingResult errores,
                          Model model) {
        if (errores.hasErrors()) {
            List<Conductores> conductores = conductoresServicio.listaConductores();
            model.addAttribute("conductores", conductores);
            return (informacion.getIdInformacion() != null) ? "modificarInformacion" : "nuevoInformacion";
        }
        informacionServicio.guardar(informacion);
        return "redirect:/informacion";
    }

    @GetMapping("/editarInformacion/{idInformacion}")
    public String editar(@PathVariable("idInformacion") Long idInformacion, Model model) {
        Informacion informacion = new Informacion();
        informacion.setIdInformacion(idInformacion);
        System.out.println("Informacion: " + informacion.getIdInformacion());
        informacion = informacionServicio.encontrarInformacion(informacion);
        model.addAttribute("informacion", informacion);
        List<Conductores> conductores = conductoresServicio.listaConductores();
        model.addAttribute("conductores", conductores);
        return "modificarInformacion";
    }

    @GetMapping("/eliminarInformacion/{idInformacion}")
    public String eliminar(@PathVariable("idInformacion") Long idInformacion, Model model) {
         Informacion informacion = new Informacion();
        informacion.setIdInformacion(idInformacion);
        informacionServicio.eliminar(informacion);
        return "redirect:/informacion";
    }

}
