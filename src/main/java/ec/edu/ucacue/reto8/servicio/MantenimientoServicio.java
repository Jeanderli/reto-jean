/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ucacue.reto8.servicio;

import ec.edu.ucacue.reto8.domain.Mantenimiento;
import java.util.List;



public interface MantenimientoServicio {

    public List<Mantenimiento> listarMantenimiento();

    public void guardar(Mantenimiento mantenimiento);

    public void eliminar(Mantenimiento mantenimiento);

    public Mantenimiento encontrarMantenimiento(Mantenimiento mantenimiento);
    
}