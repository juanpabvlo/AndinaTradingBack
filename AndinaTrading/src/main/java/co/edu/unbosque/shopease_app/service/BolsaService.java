package co.edu.unbosque.shopease_app.service;

import co.edu.unbosque.shopease_app.dto.AccionHistoricaDTO;
import co.edu.unbosque.shopease_app.dto.HistorialPorEmpresaDTO;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class BolsaService {

    private static final Logger logger = LoggerFactory.getLogger(BolsaService.class);

    private static final String API_KEY = "804de746a0c64cc2a38341511271ef00";
    private static final String BASE_URL_HISTORICO = "https://api.twelvedata.com/time_series";
    private static final String BASE_URL_PRECIO_SIMPLE = "https://api.twelvedata.com/price";
    private static final String SYMBOLS_TOP = "AAPL,MSFT,GOOG,AMZN,TSLA,META,NVDA,JPM";

    public List<AccionHistoricaDTO> obtenerHistorialAccion(String simbolo) {
        String url = BASE_URL_HISTORICO + "?symbol=" + simbolo + "&interval=1day&outputsize=30&apikey=" + API_KEY;

        logger.info("Consultando historial de acción: {}", simbolo);

        List<AccionHistoricaDTO> historial = new ArrayList<>();

        try {
            RestTemplate restTemplate = new RestTemplate();
            String json = restTemplate.getForObject(url, String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(json);
            JsonNode values = root.path("values");

            if (values.isArray()) {
                for (JsonNode item : values) {
                    String fecha = item.path("datetime").asText();
                    Double precio = item.path("close").asDouble();
                    historial.add(new AccionHistoricaDTO(fecha, precio));
                }
            } else {
                logger.warn("No se encontraron datos históricos para el símbolo: {}", simbolo);
            }

        } catch (Exception e) {
            logger.error("Error al obtener historial: {}", e.getMessage());
        }

        return historial;
    }

    // Método adicional opcional si necesitas los precios simples de las 8 empresas top
    public String obtenerPreciosTopEmpresas() {
        String url = BASE_URL_PRECIO_SIMPLE + "?symbol=" + SYMBOLS_TOP + "&apikey=" + API_KEY;

        logger.info("Consultando precios top: {}", SYMBOLS_TOP);

        try {
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            logger.error("Error al consultar precios múltiples: {}", e.getMessage());
            return "{ \"error\": \"No se pudo obtener la información del mercado.\" }";
        }
    }

    public List<HistorialPorEmpresaDTO> obtenerHistorialTopEmpresas() {
    String[] simbolos = { "AAPL", "MSFT", "GOOG", "AMZN", "TSLA", "META", "NVDA", "JPM" };
    List<HistorialPorEmpresaDTO> resultado = new ArrayList<>();

    for (String simbolo : simbolos) {
        List<AccionHistoricaDTO> historial = obtenerHistorialAccion(simbolo);
        resultado.add(new HistorialPorEmpresaDTO(simbolo, historial));
    }

    return resultado;
}
}
