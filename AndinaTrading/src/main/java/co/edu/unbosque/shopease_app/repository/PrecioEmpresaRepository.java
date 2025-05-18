package co.edu.unbosque.shopease_app.repository;

import java.util.List;

import co.edu.unbosque.shopease_app.model.PrecioEmpresaModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PrecioEmpresaRepository extends JpaRepository<PrecioEmpresaModel, Long> {
    List<PrecioEmpresaModel> findAllByOrderByFechaDesc();
}
