package co.edu.unbosque.shopease_app.controller;

import co.edu.unbosque.shopease_app.dto.InversionistaRequestDTO;
import co.edu.unbosque.shopease_app.model.InversionistaModel;
import co.edu.unbosque.shopease_app.service.InversionistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inversionistas")
public class InversionistaController {

    @Autowired
    private InversionistaService inversionistaService;

    @GetMapping
    public List<InversionistaModel> obtenerTodosLosInversionistas() {
        return inversionistaService.obtenerTodos();
    }

    @PostMapping("/buscar")
    public ResponseEntity<InversionistaModel> obtenerInversionistaPorJson(@RequestBody InversionistaRequestDTO request) {
        Optional<InversionistaModel> inversionista = inversionistaService.buscarPorId(request.getId());
        return inversionista.map(ResponseEntity::ok)
                            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
