package com.runasimi_edu.backend.dto.request;

import lombok.Data;

@Data
public class ActividadRequest {
    private String nombre;
    private String descripcion;
    private Long categoriaId;
    private Long gradoId;
    private String nivelDificultad;
    private Integer puntosBase;
    private String configuracion;
    private String multimedia;
    private String urlVideo;
    private String urlAudio;
    private String urlImagen;

    public ActividadRequest() {
    }

    public ActividadRequest(Long categoriaId, String configuracion, String descripcion, Long gradoId, String multimedia, String nivelDificultad, String nombre, Integer puntosBase, String urlAudio, String urlImagen, String urlVideo) {
        this.categoriaId = categoriaId;
        this.configuracion = configuracion;
        this.descripcion = descripcion;
        this.gradoId = gradoId;
        this.multimedia = multimedia;
        this.nivelDificultad = nivelDificultad;
        this.nombre = nombre;
        this.puntosBase = puntosBase;
        this.urlAudio = urlAudio;
        this.urlImagen = urlImagen;
        this.urlVideo = urlVideo;
    }
}
