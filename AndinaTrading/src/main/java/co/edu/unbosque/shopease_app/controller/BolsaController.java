package co.edu.unbosque.shopease_app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import co.edu.unbosque.shopease_app.dto.AccionHistoricaDTO;
import co.edu.unbosque.shopease_app.service.BolsaService;
import co.edu.unbosque.shopease_app.dto.HistorialPorEmpresaDTO;

@RestController
@CrossOrigin(origins = { "http://localhost:8090", "http://localhost:8080", "*" })
@RequestMapping("/bolsa")
public class BolsaController {

    @Autowired
    private BolsaService bolsaService;

    @GetMapping("/precios-top")
    @Operation(summary = "Precios de empresas top", description = "Obtiene los precios de las acciones de las principales empresas del mercado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Consulta exitosa"),
        @ApiResponse(responseCode = "500", description = "Error al consultar los precios")
    })
    public ResponseEntity<String> obtenerPreciosTopEmpresas() {
        String respuesta = bolsaService.obtenerPreciosTopEmpresas();
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/historial")
    @Operation(summary = "Historial de precios", description = "Obtiene los precios históricos de una acción para graficar su variación")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Consulta exitosa"),
        @ApiResponse(responseCode = "500", description = "Error al consultar el historial")
    })
    public ResponseEntity<List<AccionHistoricaDTO>> obtenerHistorial(@RequestParam String simbolo) {
        List<AccionHistoricaDTO> historial = bolsaService.obtenerHistorialAccion(simbolo);
        return ResponseEntity.ok(historial);
    }



@GetMapping("/historial-top")
@Operation(summary = "Historial de empresas top", description = "Obtiene el historial de precios de las 8 empresas principales")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Consulta exitosa"),
    @ApiResponse(responseCode = "500", description = "Error al consultar el historial")
})
public ResponseEntity<List<HistorialPorEmpresaDTO>> obtenerHistorialTopEmpresas() {
    List<HistorialPorEmpresaDTO> historiales = bolsaService.obtenerHistorialTopEmpresas();
    return ResponseEntity.ok(historiales);
}

}
