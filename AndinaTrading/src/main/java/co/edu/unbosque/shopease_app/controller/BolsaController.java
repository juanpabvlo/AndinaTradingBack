package co.edu.unbosque.shopease_app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import co.edu.unbosque.shopease_app.dto.AccionHistoricaDTO;
import co.edu.unbosque.shopease_app.dto.HistorialPorEmpresaDTO;
import co.edu.unbosque.shopease_app.model.PrecioEmpresaModel;
import co.edu.unbosque.shopease_app.service.BolsaService;

@RestController
@CrossOrigin(origins = { "http://localhost:8090", "http://localhost:8080", "*" })
@RequestMapping("/bolsa")
public class BolsaController {

    @Autowired
    private BolsaService bolsaService;

    @GetMapping("/precios-top")
    @Operation(summary = "Obtener y guardar precios de empresas top", description = "Obtiene y guarda en BD los precios de las principales acciones del mercado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Precios obtenidos y guardados exitosamente"),
        @ApiResponse(responseCode = "500", description = "Error al consultar o guardar los precios")
    })
    public ResponseEntity<String> obtenerYGuardarPreciosTopEmpresas() {
        String respuesta = bolsaService.obtenerYGuardarPreciosTopEmpresas();
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/precios-guardados")
    @Operation(summary = "Consultar precios guardados", description = "Consulta todos los precios guardados de empresas ordenados por fecha descendente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Consulta exitosa"),
        @ApiResponse(responseCode = "500", description = "Error al consultar la base de datos")
    })
    public ResponseEntity<List<PrecioEmpresaModel>> consultarPreciosGuardados() {
        List<PrecioEmpresaModel> precios = bolsaService.consultarPreciosGuardados();
        return ResponseEntity.ok(precios);
    }

    @GetMapping("/historial")
    @Operation(summary = "Historial de precios de una acción", description = "Obtiene los precios históricos de una acción para graficar su variación")
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
