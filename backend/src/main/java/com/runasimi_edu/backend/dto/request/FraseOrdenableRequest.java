package com.runasimi_edu.backend.dto.request;

import java.util.List;

import com.runasimi_edu.backend.model.Actividad.NivelDificultad;

import lombok.Data;

@Data
public class FraseOrdenableRequest {
    private Long actividadId;
    private String fraseCorrecta;
    private List<String> palabrasDesordenadas;
    private String traduccionEspanol;
    private NivelDificultad nivel;
    private String urlAudio;
    private String urlImagen;
}
