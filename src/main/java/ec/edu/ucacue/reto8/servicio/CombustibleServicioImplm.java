/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ucacue.reto8.servicio;

import ec.edu.ucacue.reto8.domain.Combustible;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ec.edu.ucacue.reto8.dao.CombustibleDao;


@Service
public class CombustibleServicioImplm implements CombustibleServicio{
    @Autowired
    private CombustibleDao combustibleDao;

    @Override
    @Transactional(readOnly = true)
    public List<Combustible> listarCombustibles() {
        return (List<Combustible>) combustibleDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Combustible combustible) {
        combustibleDao.save(combustible);
    }

    @Override
    @Transactional
    public void eliminar(Combustible combustible) {
        combustibleDao.delete(combustible);
    }

    @Override
    @Transactional(readOnly = true)
    public Combustible encontrarCombustible(Combustible combustible) {
        return combustibleDao.findById(combustible.getIdCombustible()).orElse(null);
    }
}
    

