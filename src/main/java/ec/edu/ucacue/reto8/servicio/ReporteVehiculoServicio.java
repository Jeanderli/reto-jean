
package ec.edu.ucacue.reto8.servicio;

import ec.edu.ucacue.reto8.domain.ReporteVehiculo;
import java.util.List;


public interface ReporteVehiculoServicio {

    public List<ReporteVehiculo> listarReporteVehiculo();

    public void guardar(ReporteVehiculo reporteVehiculo);

    public void eliminar(ReporteVehiculo reporteVehiculo);

    public ReporteVehiculo encontrarReporteVehiculo(ReporteVehiculo reporteVehiculo);
    
}
    