package co.edu.unbosque.shopease_app.controller;

import co.edu.unbosque.shopease_app.model.*;
import co.edu.unbosque.shopease_app.service.CiudadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Transactional
@CrossOrigin(origins = { "http://localhost:8090", "http://localhost:8080" })
@RestController
@RequestMapping("/Ciudades")
public class CiudadController {

    @Autowired
    CiudadService ciudadService;

    @PostMapping("/crear")
    @Operation(summary = "Crear ciudad", description = "Crea una ciudad de acuerdo a un cuerpo JSON.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(schema = @Schema(implementation = CiudadModel.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<String> crearCiudad(@RequestBody CiudadModel ciudad) {
        try {
            ciudadService.saveCiudad(ciudad);
            return ResponseEntity.ok("Ciudad creada exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar la ciudad: " + e.getMessage());
        }
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar ciudades", description = "Obtiene la lista de ciudades registradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ciudades encontradas"),
            @ApiResponse(responseCode = "404", description = "No se encontraron ciudades")
    })
    public ResponseEntity<List<CiudadModel>> listarCiudades() {
        List<CiudadModel> ciudades = ciudadService.findAll();
        if (ciudades != null && !ciudades.isEmpty()) {
            return ResponseEntity.ok(ciudades);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/actualizar/{id}")
    @Operation(summary = "Actualizar ciudad", description = "Actualiza una ciudad existente por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ciudad actualizada exitosamente", content = @Content(schema = @Schema(implementation = CiudadModel.class))),
            @ApiResponse(responseCode = "404", description = "Ciudad no encontrada")
    })
    public ResponseEntity<CiudadModel> actualizarCiudad(@PathVariable int id, @RequestBody CiudadModel ciudadModel) {
        CiudadModel ciudadActualizada = ciudadService.updateCiudad(id, ciudadModel);
        if (ciudadActualizada != null) {
            return ResponseEntity.ok(ciudadActualizada);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    @Operation(summary = "Eliminar ciudad", description = "Elimina una ciudad por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ciudad eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Ciudad no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<String> eliminarCiudad(@PathVariable int id) {
        try {
            boolean eliminada = ciudadService.deleteCiudad(id);
            if (eliminada) {
                return ResponseEntity.ok("Ciudad eliminada exitosamente.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ciudad no encontrada.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar la ciudad: " + e.getMessage());
        }
    }
}
