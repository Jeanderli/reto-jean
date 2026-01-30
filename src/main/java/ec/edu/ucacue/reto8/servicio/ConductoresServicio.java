/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ucacue.reto8.servicio;

import ec.edu.ucacue.reto8.domain.Conductores;
import java.util.List;


/**
 *
 * @author DAVID
 */
public interface ConductoresServicio {
    public List<Conductores> listaConductores();

    public void guardar(Conductores conductores);

    public void eliminar(Conductores conductores);

    public Conductores encontrarConductores(Conductores conductores);
    
}
