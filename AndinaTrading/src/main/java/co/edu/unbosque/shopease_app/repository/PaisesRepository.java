package co.edu.unbosque.shopease_app.repository;

import co.edu.unbosque.shopease_app.model.PaisesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaisesRepository  extends JpaRepository<PaisesModel,Integer> {

}
