package co.edu.unbosque.shopease_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.unbosque.shopease_app.model.OrdenModel;

public interface OrdenRepository extends JpaRepository<OrdenModel, Integer> {
}
