package co.edu.unbosque.shopease_app.dto;

import java.util.List;

public class HistorialPorEmpresaDTO {
    private String simbolo;
    private List<AccionHistoricaDTO> historial;

    public HistorialPorEmpresaDTO() {}

    public HistorialPorEmpresaDTO(String simbolo, List<AccionHistoricaDTO> historial) {
        this.simbolo = simbolo;
        this.historial = historial;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public List<AccionHistoricaDTO> getHistorial() {
        return historial;
    }

    public void setHistorial(List<AccionHistoricaDTO> historial) {
        this.historial = historial;
    }
}
