package co.edu.unbosque.shopease_app.service;

import co.edu.unbosque.shopease_app.model.CiudadModel;
import co.edu.unbosque.shopease_app.repository.CiudadRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CiudadService {

    private final Logger logger = LoggerFactory.getLogger(CiudadModel.class);

    @Autowired
    private CiudadRepository ciudadRepository;

    public CiudadModel saveCiudad(CiudadModel ciudad) {
        return ciudadRepository.save(ciudad);
    }

    public CiudadModel updateCiudad(int id, CiudadModel ciudad) {
        if (ciudadRepository.existsById(id)) {
            ciudad.setId(id);
            return ciudadRepository.save(ciudad);
        } else {
            return null;
        }
    }

    public Boolean deleteCiudad(int id) {
        if (ciudadRepository.existsById(id)) {
            ciudadRepository.deleteById(id);
            return true;
        } else {
            throw new EntityNotFoundException("La ciudad con ID " + id + " no existe.");
        }
    }

    public List<CiudadModel> findAll() {
        return ciudadRepository.findAll();
    }
}
