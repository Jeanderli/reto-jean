
package ec.edu.ucacue.reto8.servicio;

import ec.edu.ucacue.reto8.domain.Combustible;
import java.util.List;


public interface CombustibleServicio {

    public List<Combustible> listarCombustibles();

    public void guardar(Combustible combustible);

    public void eliminar(Combustible combustible);

    public Combustible encontrarCombustible(Combustible combustible);
    
}
    