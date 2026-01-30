/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ec.edu.ucacue.reto8.servicio;
import ec.edu.ucacue.reto8.domain.Informacion;
import java.util.List;

/**
 *
 * @author jeand
 */
public interface InformacionServicio {
    public List<Informacion> listarInformacion();

    public void guardar(Informacion informacion);

    public void eliminar(Informacion informacion);

    public Informacion encontrarInformacion(Informacion informacion);
}
