package co.edu.unbosque.shopease_app.controller;

import co.edu.unbosque.shopease_app.model.OrdenModel;
import co.edu.unbosque.shopease_app.service.OrdenService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordenes")

public class OrdenController {

    @Autowired
    private OrdenService ordenService;

    @PostMapping
    @Operation(summary = "Crear nueva orden de compra o venta")
    public ResponseEntity<OrdenModel> crearOrden(@RequestBody OrdenModel orden) {
        // Crear la orden y enviar el correo de confirmación
        OrdenModel nueva = ordenService.crearOrden(orden);
        return ResponseEntity.ok(nueva);
    }
}
