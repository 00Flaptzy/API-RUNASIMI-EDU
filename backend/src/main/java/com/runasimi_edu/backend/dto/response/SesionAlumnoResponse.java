package com.runasimi_edu.backend.dto.response;

import java.util.Date;

import com.runasimi_edu.backend.model.SesionAlumno.EstadoSesion;

import lombok.Data;

@Data
public class SesionAlumnoResponse {
    private Long id;

    private Long alumnoId;
    private String alumnoNombre;

    private Long actividadId;
    private String actividadNombre;

    private Date fechaInicio;
    private Date fechaFin;

    private Boolean completado;
    private Integer puntosObtenidos;
    private Integer intentos;
    private Integer tiempoSegundos;

    private EstadoSesion estado;
    private Integer ultimoItem;

    private String detalles;

    public SesionAlumnoResponse() {
    }

    public SesionAlumnoResponse(Long actividadId, String actividadNombre, Long alumnoId, String alumnoNombre, Boolean completado, String detalles, EstadoSesion estado, Date fechaFin, Date fechaInicio, Long id, Integer intentos, Integer puntosObtenidos, Integer tiempoSegundos, Integer ultimoItem) {
        this.actividadId = actividadId;
        this.actividadNombre = actividadNombre;
        this.alumnoId = alumnoId;
        this.alumnoNombre = alumnoNombre;
        this.completado = completado;
        this.detalles = detalles;
        this.estado = estado;
        this.fechaFin = fechaFin;
        this.fechaInicio = fechaInicio;
        this.id = id;
        this.intentos = intentos;
        this.puntosObtenidos = puntosObtenidos;
        this.tiempoSegundos = tiempoSegundos;
        this.ultimoItem = ultimoItem;
    }
}
