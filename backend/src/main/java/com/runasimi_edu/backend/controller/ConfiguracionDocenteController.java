package com.runasimi_edu.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.runasimi_edu.backend.dto.request.ConfiguracionDocenteRequest;
import com.runasimi_edu.backend.dto.response.ConfiguracionDocenteResponse;
import com.runasimi_edu.backend.service.ConfiguracionDocenteService;

@RestController
@RequestMapping("/api/configuracion-docente")
public class ConfiguracionDocenteController {

    @Autowired
    private ConfiguracionDocenteService configuracionDocenteService;

    // Guardar o actualizar configuración
    @PostMapping
    public ResponseEntity<ConfiguracionDocenteResponse> guardar(@RequestBody ConfiguracionDocenteRequest request) {
        ConfiguracionDocenteResponse response = configuracionDocenteService.guardar(request);
        return ResponseEntity.ok(response);
    }

    // Obtener configuración por ID
    @GetMapping("/{id}")
    public ResponseEntity<ConfiguracionDocenteResponse> buscarPorId(@PathVariable Long id) {
        ConfiguracionDocenteResponse response = configuracionDocenteService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    // Obtener configuración por ID del docente
    @GetMapping("/docente/{docenteId}")
    public ResponseEntity<ConfiguracionDocenteResponse> buscarPorDocente(@PathVariable Long docenteId) {
        ConfiguracionDocenteResponse response = configuracionDocenteService.buscarPorDocenteId(docenteId);
        return ResponseEntity.ok(response);
    }

    // Eliminar configuración por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPorId(@PathVariable Long id) {
        configuracionDocenteService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
