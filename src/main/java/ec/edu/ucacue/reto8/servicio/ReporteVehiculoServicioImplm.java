/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ucacue.reto8.servicio;

import ec.edu.ucacue.reto8.domain.ReporteVehiculo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ec.edu.ucacue.reto8.dao.ReporteVehiculoDao;


@Service
public class ReporteVehiculoServicioImplm implements ReporteVehiculoServicio{
    @Autowired
    private ReporteVehiculoDao reporteVehiculoDao;

    @Override
    @Transactional(readOnly = true)
    public List<ReporteVehiculo> listarReporteVehiculo() {
        return (List<ReporteVehiculo>) reporteVehiculoDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(ReporteVehiculo reporteVehiculo) {
        reporteVehiculoDao.save(reporteVehiculo);
    }

    @Override
    @Transactional
    public void eliminar(ReporteVehiculo reporteVehiculo) {
        reporteVehiculoDao.delete(reporteVehiculo);
    }

    @Override
    @Transactional(readOnly = true)
    public ReporteVehiculo encontrarReporteVehiculo(ReporteVehiculo reporteVehiculo) {
        return reporteVehiculoDao.findById(reporteVehiculo.getIdReporteVehiculo()).orElse(null);
    }
}
    

