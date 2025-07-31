package com.runasimi_edu.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runasimi_edu.backend.dto.request.ConfiguracionDocenteRequest;
import com.runasimi_edu.backend.dto.response.ConfiguracionDocenteResponse;
import com.runasimi_edu.backend.model.ConfiguracionDocente;
import com.runasimi_edu.backend.model.Usuario;
import com.runasimi_edu.backend.repository.ConfiguracionDocenteRepository;
import com.runasimi_edu.backend.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class ConfiguracionDocenteService {

    @Autowired
    private ConfiguracionDocenteRepository configuracionDocenteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Guardar o actualizar configuración
    public ConfiguracionDocenteResponse guardar(ConfiguracionDocenteRequest request) {
        // Validar que el docente exista
        Usuario docente = usuarioRepository.findById(request.getDocenteId())
                .orElseThrow(() -> new IllegalArgumentException("El docente con ID " + request.getDocenteId() + " no existe."));

        // Buscar configuración existente por docente
        Optional<ConfiguracionDocente> existente = configuracionDocenteRepository.findByDocente(docente);

        ConfiguracionDocente configuracion = existente.orElseGet(() -> new ConfiguracionDocente());
        configuracion.setDocente(docente);
        configuracion.setVistaPreferida(
                request.getVistaPreferida() != null ? request.getVistaPreferida() : ConfiguracionDocente.VistaPreferida.TABLA);
        configuracion.setFiltrosGuardados(request.getFiltrosGuardados());
        configuracion.setNotificaciones(
                request.getNotificaciones() != null ? request.getNotificaciones() : true);

        ConfiguracionDocente guardada = configuracionDocenteRepository.save(configuracion);
        return new ConfiguracionDocenteResponse(guardada);
    }

    // Buscar por ID
    public ConfiguracionDocenteResponse buscarPorId(Long id) {
        ConfiguracionDocente configuracion = configuracionDocenteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe configuración con ID: " + id));
        return new ConfiguracionDocenteResponse(configuracion);
    }

    // Buscar por ID de docente
    public ConfiguracionDocenteResponse buscarPorDocenteId(Long docenteId) {
        Usuario docente = usuarioRepository.findById(docenteId)
                .orElseThrow(() -> new IllegalArgumentException("El docente con ID " + docenteId + " no existe."));

        ConfiguracionDocente configuracion = configuracionDocenteRepository.findByDocente(docente)
                .orElseThrow(() -> new IllegalArgumentException("No existe configuración para el docente con ID: " + docenteId));

        return new ConfiguracionDocenteResponse(configuracion);
    }

    // Eliminar por ID
    @Transactional
    public void eliminarPorId(Long id) {
        if (!configuracionDocenteRepository.existsById(id)) {
            throw new IllegalArgumentException("No existe configuración con ID: " + id);
        }
        configuracionDocenteRepository.deleteById(id);
    }
}
