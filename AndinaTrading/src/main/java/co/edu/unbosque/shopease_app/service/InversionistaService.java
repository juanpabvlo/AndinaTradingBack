package co.edu.unbosque.shopease_app.service;

import co.edu.unbosque.shopease_app.model.InversionistaModel;
import co.edu.unbosque.shopease_app.repository.InversionistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InversionistaService {

    @Autowired
    private InversionistaRepository repository;

    public List<InversionistaModel> obtenerTodos() {
        return repository.findAll();
    }

    public Optional<InversionistaModel> buscarPorId(int id) {
        return repository.findById(id);
    }
}
