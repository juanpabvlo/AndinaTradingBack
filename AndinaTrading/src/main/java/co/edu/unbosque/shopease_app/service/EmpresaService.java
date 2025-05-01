package co.edu.unbosque.shopease_app.service;

import co.edu.unbosque.shopease_app.model.PaisesModel;
import co.edu.unbosque.shopease_app.model.EmpresaModel;
import co.edu.unbosque.shopease_app.repository.PaisesRepository;
import co.edu.unbosque.shopease_app.repository.EmpresaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class EmpresaService {
	private final Logger logger = LoggerFactory.getLogger(EmpresaService.class);

	@Autowired
	private EmpresaRepository productoRepository;

	@Autowired 
	private PaisService categoriaService;


	public EmpresaModel saveProducto(EmpresaModel producto) {

		EmpresaModel productoModel = productoRepository.save(producto);
		return productoModel;
	}

	public EmpresaModel updateProducto(int id, EmpresaModel producto) {
		if (productoRepository.existsById(id)) {
			producto.setId(id);
			return productoRepository.save(producto);
		} else {
			return null;
		}

	}

	public Boolean deleteProducto(int id) {
		if (productoRepository.existsById(id)) {
			productoRepository.deleteById(id); 
			return true;
		} else {
			throw new EntityNotFoundException("El producto con ID " + id + " no existe.");
		}
	}

	public List<EmpresaModel> findAll(){
		return productoRepository.findAll();
	}

	public EmpresaModel findById(int id) {
		return productoRepository.findById(id).orElse(null);
	}
}
