package com.runasimi_edu.backend.dto.request;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.runasimi_edu.backend.model.ConfiguracionDocente.VistaPreferida;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ConfiguracionDocenteRequest {

    @NotNull(message = "El ID del docente es obligatorio")
    private Long docenteId;

    @JsonSetter(nulls = Nulls.SKIP)
    private VistaPreferida vistaPreferida = VistaPreferida.TABLA;

    @JsonSetter(nulls = Nulls.SKIP)
    private Boolean notificaciones = true;

    private String filtrosGuardados;
}
