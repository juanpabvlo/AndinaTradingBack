package co.edu.unbosque.shopease_app.service;

import co.edu.unbosque.shopease_app.model.FacturaRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PdfCoService {

    @Value("${pdfco.api.key}")
    private String apiKey;

    private static final String PDF_CO_URL = "https://api.pdf.co/v1/pdf/convert/from/html";

    public String generatePdfFromTemplate(FacturaRequest facturaRequest) {
        try {
            // Crear los datos que van en el campo "templateData"
            Map<String, Object> data = new HashMap<>();
            data.put("fecha", facturaRequest.getFecha());
            data.put("orden_id", facturaRequest.getOrden_id());
            data.put("cliente", facturaRequest.getCliente());
            data.put("accion", facturaRequest.getAccion());
            data.put("cantidad", facturaRequest.getCantidad());
            data.put("precio_unitario", facturaRequest.getPrecio_unitario());
            data.put("total", facturaRequest.getTotal());
            data.put("total_factura", facturaRequest.getTotal_factura());
            data.put("logo_url", facturaRequest.getLogo_url());

            // Convertir a JSON string
            ObjectMapper mapper = new ObjectMapper();
            String jsonData = mapper.writeValueAsString(data);

            // Crear el JSON final con templateId y templateData como string
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("templateId", "4816"); // Como string
            requestBody.put("templateData", jsonData);
            requestBody.put("name", "factura.pdf");

            // Headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("x-api-key", apiKey);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<Map> response = restTemplate.postForEntity(PDF_CO_URL, request, Map.class);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                String url = (String) response.getBody().get("url");
                return url;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}