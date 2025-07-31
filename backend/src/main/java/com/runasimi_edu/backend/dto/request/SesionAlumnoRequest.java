package com.runasimi_edu.backend.dto.request;

import java.util.Date;

import com.runasimi_edu.backend.model.SesionAlumno.EstadoSesion;

import lombok.Data;

@Data
public class SesionAlumnoRequest {
    private Long alumnoId;
    private Long actividadId;

    private Date fechaInicio;
    private Date fechaFin;

    private Boolean completado;
    private Integer puntosObtenidos;
    private Integer intentos;
    private Integer tiempoSegundos;

    private EstadoSesion estado;
    private Integer ultimoItem;

    private String detalles;

    public SesionAlumnoRequest() {
    }

    public SesionAlumnoRequest(Long actividadId, Long alumnoId, Boolean completado, String detalles, EstadoSesion estado, Date fechaFin, Date fechaInicio, Integer intentos, Integer puntosObtenidos, Integer tiempoSegundos, Integer ultimoItem) {
        this.actividadId = actividadId;
        this.alumnoId = alumnoId;
        this.completado = completado;
        this.detalles = detalles;
        this.estado = estado;
        this.fechaFin = fechaFin;
        this.fechaInicio = fechaInicio;
        this.intentos = intentos;
        this.puntosObtenidos = puntosObtenidos;
        this.tiempoSegundos = tiempoSegundos;
        this.ultimoItem = ultimoItem;
    }
}
    