package co.edu.unbosque.shopease_app.repository;

import co.edu.unbosque.shopease_app.model.CiudadModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CiudadRepository extends JpaRepository<CiudadModel, Integer> {
}
