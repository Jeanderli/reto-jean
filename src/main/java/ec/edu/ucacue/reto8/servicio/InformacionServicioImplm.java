/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ec.edu.ucacue.reto8.servicio;
import ec.edu.ucacue.reto8.domain.Informacion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ec.edu.ucacue.reto8.dao.InformacionDao;

/**
 *
 * @author jeand
 */

@Service
public class InformacionServicioImplm implements InformacionServicio {
 @Autowired
    private InformacionDao informacionDao;

    @Override
    @Transactional(readOnly = true)
    public List<Informacion> listarInformacion() {
        return (List<Informacion>) informacionDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Informacion informacion) {
        informacionDao.save(informacion);
    }

    @Override
    @Transactional
    public void eliminar(Informacion informacion) {
        informacionDao.delete(informacion);
    }

    @Override
    @Transactional(readOnly = true)
    public Informacion encontrarInformacion(Informacion informacion) {
        return informacionDao.findById(informacion.getIdInformacion()).orElse(null);
    }
}
