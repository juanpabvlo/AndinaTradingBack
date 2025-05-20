package co.edu.unbosque.shopease_app.controller;

import co.edu.unbosque.shopease_app.model.OrdenModel;
import co.edu.unbosque.shopease_app.service.OrdenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

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
    @Operation(summary = "Crear nueva orden de compra o venta", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos de la orden", required = true, content = @Content(schema = @Schema(implementation = OrdenModel.class))))
    public ResponseEntity<OrdenModel> crearOrden(@RequestBody OrdenModel orden) {
        OrdenModel nueva = ordenService.crearOrden(orden);
        return ResponseEntity.ok(nueva);
    }

}
