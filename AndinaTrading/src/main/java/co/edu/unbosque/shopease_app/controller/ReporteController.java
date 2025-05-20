package co.edu.unbosque.shopease_app.controller;

import co.edu.unbosque.shopease_app.dto.ReporteRequest;
import co.edu.unbosque.shopease_app.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @PostMapping("/balance")
    public ResponseEntity<?> enviarReporteBalance(@RequestBody ReporteRequest request) {
        int idInversionista = request.getId();

        try {
            reporteService.enviarReporteBalance(idInversionista);
            return ResponseEntity.ok("Reporte enviado correctamente al correo del inversionista");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al enviar el reporte: " + e.getMessage());
        }
    }
}
