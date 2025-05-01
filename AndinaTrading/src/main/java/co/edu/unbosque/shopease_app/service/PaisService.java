package co.edu.unbosque.shopease_app.service;

import co.edu.unbosque.shopease_app.model.PaisesModel;
import co.edu.unbosque.shopease_app.repository.PaisesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PaisService {

    private final Logger logger = LoggerFactory.getLogger(PaisesModel.class);
    @Autowired
    private PaisesRepository categoriaRepository;


    public PaisesModel saveCategoria(PaisesModel categorias) {

        PaisesModel categoriaModel = categoriaRepository.save(categorias);
        return categoriaModel;
    }

    public PaisesModel updateCategoria(int id, PaisesModel categoria) {
        if (categoriaRepository.existsById(id)) {
            categoria.setId(id);
            return categoriaRepository.save(categoria);
        } else {
            return null;
        }

    }

    public Boolean deleteCategoria(int id) {
        if (categoriaRepository.existsById(id)) { // Verifica si la categoría existe
            categoriaRepository.deleteById(id); // Elimina directamente por id
            return true;
        } else {
            throw new EntityNotFoundException("La categoría con ID " + id + " no existe.");
        }
    }

    public List<PaisesModel> findAll(){
        return categoriaRepository.findAll();
    }

}
