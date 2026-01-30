/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ucacue.reto8.web;

import ec.edu.ucacue.reto8.domain.Conductores;
import ec.edu.ucacue.reto8.domain.ReporteVehiculo;
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
import ec.edu.ucacue.reto8.servicio.ReporteVehiculoServicio;
import java.util.List;
/**
 *
 * @author Jorge Luis
 */

@Controller
@Slf4j
public class ReporteVehiculoControlador {
    
    @Autowired
    private ReporteVehiculoServicio reporteVehiculoServicio;
    
    @Autowired
    private ConductoresServicio conductoresServicio;

//    @GetMapping("/")
//    public String inicio(Model model, @AuthenticationPrincipal User user) {
//        var reporteVehiculos = reporteVehiculoServicio.listarReporteVehiculo();
//        log.info("ejecutando el controlador Spring MVC");
//        log.info("usuario que hizo login:" + user);
//        model.addAttribute("reporteVehiculos", reporteVehiculos);
//        return "index";
//    }

    @GetMapping("/reporteVehiculo")
    public String mostarReporteVehiculo(Model model) {
        var reporteVehiculos = reporteVehiculoServicio.listarReporteVehiculo();
        model.addAttribute("reporteVehiculos", reporteVehiculos);
        return "reporteVehiculo";
    }
    
    @GetMapping("/nuevoReporteVehiculo")
    public String agregar(Model model) {
        model.addAttribute("reporteVehiculo", new ReporteVehiculo());
        
        List<Conductores> conductores = conductoresServicio.listaConductores();
        model.addAttribute("conductores", conductores);

        return "nuevoReporteVehiculo";
    }

    @PostMapping("/guardarReporteVehiculo")
    public String guardar(@Valid @ModelAttribute("reporteVehiculo") ReporteVehiculo reporteVehiculo,
                          BindingResult errores,
                          Model model) {
        if (errores.hasErrors()) {
            List<Conductores> conductores = conductoresServicio.listaConductores();
            model.addAttribute("conductores", conductores);
            return (reporteVehiculo.getIdReporteVehiculo() != null) ? "modificarReporteVehiculo" : "nuevoReporteVehiculo";
        }
        reporteVehiculoServicio.guardar(reporteVehiculo);
        return "redirect:/reporteVehiculo";
    }

    @GetMapping("/editarReporteVehiculo/{idReporteVehiculo}")
    public String editar(@PathVariable("idReporteVehiculo") Long idReporteVehiculo, Model model) {
        ReporteVehiculo reporteVehiculo = new ReporteVehiculo();
        reporteVehiculo.setIdReporteVehiculo(idReporteVehiculo);
        System.out.println("ReporteVehiculo: " + reporteVehiculo.getIdReporteVehiculo());
        reporteVehiculo = reporteVehiculoServicio.encontrarReporteVehiculo(reporteVehiculo);
        model.addAttribute("reporteVehiculo", reporteVehiculo);
        
        List<Conductores> conductores = conductoresServicio.listaConductores();
        model.addAttribute("conductores", conductores);
        
        return "modificarReporteVehiculo";
    }

    @GetMapping("/eliminarReporteVehiculo/{idReporteVehiculo}")
    public String eliminar(@PathVariable("idReporteVehiculo") Long idReporteVehiculo, Model model) {
         ReporteVehiculo reporteVehiculo = new ReporteVehiculo();
        reporteVehiculo.setIdReporteVehiculo(idReporteVehiculo);
        reporteVehiculoServicio.eliminar(reporteVehiculo);
        return "redirect:/reporteVehiculo";
    }
  
}
