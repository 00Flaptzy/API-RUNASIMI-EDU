package com.runasimi_edu.backend.dto.response;

import com.runasimi_edu.backend.model.ConfiguracionDocente;
import com.runasimi_edu.backend.model.ConfiguracionDocente.VistaPreferida;

import lombok.Data;

@Data                                                            
public class ConfiguracionDocenteResponse {
    private Long id;
    private Long docenteId;
    private VistaPreferida vistaPreferida;
    private String filtrosGuardados;
    private Boolean notificaciones;

    // Constructor que recibe la entidad y extrae los datos
    public ConfiguracionDocenteResponse(ConfiguracionDocente entity) {
        this.id = entity.getId();
        this.docenteId = entity.getDocente().getId(); // <- Aquí extraemos el ID del docente
        this.vistaPreferida = entity.getVistaPreferida();
        this.filtrosGuardados = entity.getFiltrosGuardados();
        this.notificaciones = entity.getNotificaciones();
    }
}
