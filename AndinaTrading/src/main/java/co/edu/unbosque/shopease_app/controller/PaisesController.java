package co.edu.unbosque.shopease_app.controller;

import co.edu.unbosque.shopease_app.model.PaisesModel;
import co.edu.unbosque.shopease_app.service.PaisService;
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
@RequestMapping("/Paises")
public class PaisesController {

    @Autowired
    PaisService paisService;

    @PostMapping("/crear")
    @Operation(summary = "Crear pais", description = "Crea una categoría de acuerdo a un cuerpo JSON.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(schema = @Schema(implementation = PaisesModel.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<String> crearCategoria(@RequestBody PaisesModel pais) {
        try {

            PaisesModel nuevoPais = paisService.saveCategoria(pais);
            return ResponseEntity.ok("Se insertó la categoría");
        } catch (Exception e) {
            e.printStackTrace(); // Considera usar un logger en lugar de imprimir la traza
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar la categoría: " + e.getMessage());
        }
    }
    @GetMapping("/listar")
    @Operation(summary = "Obtener lista de paises ", description = "Obtener lista de paises")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paises encontradao"),
            @ApiResponse(responseCode = "404", description = "Paises no encontrados")
    })
    public ResponseEntity<List<PaisesModel>> listarTodasCategorias() {
        List <PaisesModel> pais = paisService.findAll();
        if (pais != null) {
            return ResponseEntity.ok(pais);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/actualizar/{id}")
    @Operation(summary = "Actualizar un país", description = "Actualiza un país existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "País actualizado exitosamente", content = @Content(schema = @Schema(implementation = PaisesModel.class))),
            @ApiResponse(responseCode = "404", description = "País no encontrado", content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<PaisesModel> actualizarCategoria(@PathVariable int id, @RequestBody PaisesModel categoriaModel) {

        PaisesModel actualizarCategoria= paisService.updateCategoria(id,categoriaModel);
        if (actualizarCategoria != null) {
            return ResponseEntity.ok(actualizarCategoria);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @DeleteMapping("/eliminar/{id}")
    @Operation(summary = "Eliminar País", description = "Elimina un país por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "País eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "País no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<String> eliminarCategoria(@PathVariable int id) {
        try {
            boolean isRemoved = paisService.deleteCategoria(id);
            if (isRemoved) {
                return ResponseEntity.ok("País eliminado exitosamente.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("País no encontrada.");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Considera usar un logger
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar el país: " + e.getMessage());
        }
    }
}
