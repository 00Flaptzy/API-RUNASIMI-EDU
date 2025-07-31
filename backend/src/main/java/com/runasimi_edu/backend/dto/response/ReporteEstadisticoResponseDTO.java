package com.runasimi_edu.backend.dto.response;

import java.util.Date;

import com.runasimi_edu.backend.model.ReporteEstadistico.TipoReporte;

import lombok.Data;

@Data
public class ReporteEstadisticoResponseDTO {
    private Long id;
    private Long docenteId;
    private String nombreDocente;
    private Long gradoId;
    private String nombreGrado;
    private Date fechaGeneracion;
    private String rangoFechas;
    private TipoReporte tipoReporte;
    private String datos;
    private String compartidoCon;

    public ReporteEstadisticoResponseDTO() {
    }

    public ReporteEstadisticoResponseDTO(String compartidoCon, String datos, Long docenteId, Date fechaGeneracion, Long gradoId, Long id, String nombreDocente, String nombreGrado, String rangoFechas, TipoReporte tipoReporte) {
        this.compartidoCon = compartidoCon;
        this.datos = datos;
        this.docenteId = docenteId;
        this.fechaGeneracion = fechaGeneracion;
        this.gradoId = gradoId;
        this.id = id;
        this.nombreDocente = nombreDocente;
        this.nombreGrado = nombreGrado;
        this.rangoFechas = rangoFechas;
        this.tipoReporte = tipoReporte;
    }
}
