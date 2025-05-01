package co.edu.unbosque.shopease_app.repository;

import co.edu.unbosque.shopease_app.model.EmpresaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<EmpresaModel,Integer> {


    EmpresaModel findByNombre(String nombre);

}
