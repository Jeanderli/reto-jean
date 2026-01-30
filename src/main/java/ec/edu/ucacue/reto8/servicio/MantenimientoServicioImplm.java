/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ucacue.reto8.servicio;

import ec.edu.ucacue.reto8.dao.MantenimientoDao;
import ec.edu.ucacue.reto8.domain.Mantenimiento;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MantenimientoServicioImplm implements MantenimientoServicio{
    @Autowired
    private MantenimientoDao mantenimientoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Mantenimiento> listarMantenimiento() {
        return (List<Mantenimiento>) mantenimientoDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Mantenimiento mantenimiento) {
        mantenimientoDao.save(mantenimiento);
    }

    @Override
    @Transactional
    public void eliminar(Mantenimiento mantenimiento) {
        mantenimientoDao.delete(mantenimiento);
    }

    @Override
    @Transactional(readOnly = true)
    public Mantenimiento encontrarMantenimiento(Mantenimiento mantenimiento) {
        return mantenimientoDao.findById(mantenimiento.getIdMantenimiento()).orElse(null);
    }
}
