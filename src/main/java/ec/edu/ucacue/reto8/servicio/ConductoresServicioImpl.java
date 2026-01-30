/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ucacue.reto8.servicio;

import ec.edu.ucacue.reto8.dao.ConductoresDao;
import ec.edu.ucacue.reto8.domain.Conductores;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ConductoresServicioImpl implements ConductoresServicio{

    @Autowired
    private ConductoresDao conductoresDao;

    @Override
    @Transactional(readOnly = true)
    public List<Conductores> listaConductores() {
        return (List<Conductores>) conductoresDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Conductores conductores) {
        conductoresDao.save(conductores);
    }

    @Override
    @Transactional
    public void eliminar(Conductores conductores) {
        conductoresDao.delete(conductores);
    }

    @Override
    @Transactional(readOnly = true)
    public Conductores encontrarConductores(Conductores conductores) {
        return conductoresDao.findById(conductores.getIdConductores()).orElse(null);
    }
}
    

