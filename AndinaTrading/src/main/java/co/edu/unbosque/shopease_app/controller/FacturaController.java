package co.edu.unbosque.shopease_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.unbosque.shopease_app.model.FacturaRequest;
import co.edu.unbosque.shopease_app.service.PdfCoService;

@RestController
@RequestMapping("/factura")
public class FacturaController {

    @Autowired
    private PdfCoService pdfCoService;

    @PostMapping("/generar")
    public ResponseEntity<String> generarFactura(@RequestBody FacturaRequest facturaRequest) {
        String pdfUrl = pdfCoService.generatePdfFromTemplate(facturaRequest);
        System.out.println("URL del PDF generado: " + pdfUrl);

        if (pdfUrl != null) {
            return ResponseEntity.ok(pdfUrl);  // Solo la URL como texto plano
        } else {
            return ResponseEntity.status(500).body("Error al generar la factura.");
        }
    }
}
