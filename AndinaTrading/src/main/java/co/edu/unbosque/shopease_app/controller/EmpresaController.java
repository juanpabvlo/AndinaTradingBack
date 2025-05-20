package co.edu.unbosque.shopease_app.controller;


import co.edu.unbosque.shopease_app.model.PaisesModel;
import co.edu.unbosque.shopease_app.model.EmpresaModel;
import co.edu.unbosque.shopease_app.service.PaisService;
import co.edu.unbosque.shopease_app.service.EmpresaService;
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
import java.util.Map;
import java.util.TreeMap;

@Transactional
@CrossOrigin(origins = { "http://localhost:8090", "http://localhost:8080" })
@RestController
@RequestMapping("/Empresa")
public class EmpresaController {

	@Autowired
	private EmpresaService productoService;

	@Autowired
	private PaisService categoriaService;

	@PostMapping("/crear")
	@Operation(summary = "Crear Empresa", description = "Crea una empresa que cotiza en la bolsa.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(schema = @Schema(implementation = EmpresaModel.class))),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	public ResponseEntity<String> crearProducto(@RequestBody EmpresaModel producto) {
		try {

			EmpresaModel nuevoProducto = productoService.saveProducto(producto);
			return ResponseEntity.ok("Se insertó la empresa");
		} catch (Exception e) {
			e.printStackTrace(); // Considera usar un logger en lugar de imprimir la traza
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al guardar la empresa: " + e.getMessage());
		}
	}
	@GetMapping("/listar")
	@Operation(summary = "Obtener lista de empresas", description = "Obtener lista de empresas que cotizan en la bolsa.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Empresas encontradas", content = @Content(schema = @Schema(implementation = EmpresaModel.class))),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor"),
			@ApiResponse(responseCode = "404", description = "Empreses no encontradas", content = @Content(schema = @Schema(implementation = String.class)))
	})
	public ResponseEntity<List<EmpresaModel>> listarTodosProductos() {
		List <EmpresaModel> productos = productoService.findAll();
		if (productos != null) {
			return ResponseEntity.ok(productos);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	@PutMapping("/actualizar/{id}")
	@Operation(summary = "Actualizar un producto", description = "Actualiza un producto existente.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Producto actualizado exitosamente", content = @Content(schema = @Schema(implementation = EmpresaModel.class))),
			@ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content(schema = @Schema(implementation = String.class)))
	})
	public ResponseEntity<EmpresaModel> actualizaProductos(@PathVariable int id, @RequestBody EmpresaModel productoModel) {

		EmpresaModel actualizarProducto= productoService.updateProducto(id,productoModel);
		if (actualizarProducto != null) {
			return ResponseEntity.ok(actualizarProducto);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	@DeleteMapping("/eliminar/{id}")
	@Operation(summary = "Eliminar Producto", description = "Elimina una Producto por su ID.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Producto eliminada exitosamente"),
			@ApiResponse(responseCode = "404", description = "Producto no encontrada"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	public ResponseEntity<String> eliminarProducto(@PathVariable int id) {
		try {
			boolean isRemoved = productoService.deleteProducto(id);
			if (isRemoved) {
				return ResponseEntity.ok("Producto eliminada exitosamente.");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrada.");
			}
		} catch (Exception e) {
			e.printStackTrace(); // Considera usar un logger
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al eliminar la producto: " + e.getMessage());
		}
	}

}
