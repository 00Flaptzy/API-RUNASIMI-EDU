package com.runasimi_edu.backend.dto.request;

import lombok.Data;

@Data
public class PalabraIncompletaRequest {
    private Long actividadId;
    private String palabraCompleta;
    private String palabraIncompleta;
    private String categoriaVocabulario;
    private String rutaImagen;
    private String rutaAudio;

    public PalabraIncompletaRequest() {
    }

    public PalabraIncompletaRequest(Long actividadId, String categoriaVocabulario, String palabraCompleta, String palabraIncompleta, String rutaAudio, String rutaImagen) {
        this.actividadId = actividadId;
        this.categoriaVocabulario = categoriaVocabulario;
        this.palabraCompleta = palabraCompleta;
        this.palabraIncompleta = palabraIncompleta;
        this.rutaAudio = rutaAudio;
        this.rutaImagen = rutaImagen;
    }
}
