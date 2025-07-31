package com.runasimi_edu.backend.dto.request;

import com.runasimi_edu.backend.model.Logro.TipoLogro;

import lombok.Data;

@Data
public class LogroRequest {
    private Long alumnoId;
    private Long actividadId; // puede ser null
    private TipoLogro tipo;
    private Integer cantidad;
    private String descripcion;
    private String fechaObtencion; // Formato ISO 8601 (ej. "2023-10-01T12:00:00Z")
    public LogroRequest() {
    }

    public LogroRequest(Long actividadId, Long alumnoId, Integer cantidad, String descripcion, String fechaObtencion, TipoLogro tipo) {
        this.actividadId = actividadId;
        this.alumnoId = alumnoId;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.fechaObtencion = fechaObtencion;
        this.tipo = tipo;
    }
   
}
