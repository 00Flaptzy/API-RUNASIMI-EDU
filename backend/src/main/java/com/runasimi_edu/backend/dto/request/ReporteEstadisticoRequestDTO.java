

package com.runasimi_edu.backend.dto.request;

import com.runasimi_edu.backend.model.ReporteEstadistico.TipoReporte;

import lombok.Data;

@Data
public class ReporteEstadisticoRequestDTO {
    private Long usuarioId;     // ID del docente (Usuario con rol DOCENTE)
    private Long gradoId;       // ID del grado al que se refiere el reporte
    private String rangoFechas; // Texto como "Julio 2025", "01/07/2025 - 28/07/2025", etc.
    private TipoReporte tipoReporte;
    private String datos;       // Texto explicativo del reporte
    private String compartidoCon;

    public ReporteEstadisticoRequestDTO() {
    }

    public ReporteEstadisticoRequestDTO(String compartidoCon, String datos, Long gradoId, String rangoFechas, TipoReporte tipoReporte, Long usuarioId) {
        this.compartidoCon = compartidoCon;
        this.datos = datos;
        this.gradoId = gradoId;
        this.rangoFechas = rangoFechas;
        this.tipoReporte = tipoReporte;
        this.usuarioId = usuarioId;
    }
}
