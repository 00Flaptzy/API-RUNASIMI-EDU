package com.runasimi_edu.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.runasimi_edu.backend.dto.request.PalabraIncompletaRequest;
import com.runasimi_edu.backend.dto.response.PalabraIncompletaResponse;
import com.runasimi_edu.backend.service.PalabraIncompletaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/palabras-incompletas")
@RequiredArgsConstructor
public class PalabraIncompletaController {

    private final PalabraIncompletaService palabraIncompletaService;

    // Crear nueva palabra
    @PostMapping
    public ResponseEntity<PalabraIncompletaResponse> crear(@RequestBody PalabraIncompletaRequest request) {
        return ResponseEntity.ok(palabraIncompletaService.crear(request));
    }

    // Listar todas las palabras
    @GetMapping
    public ResponseEntity<List<PalabraIncompletaResponse>> listarTodas() {
        return ResponseEntity.ok(palabraIncompletaService.listarTodas());
    }

    // Obtener palabra por ID
    @GetMapping("/{id}")
    public ResponseEntity<PalabraIncompletaResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(palabraIncompletaService.obtenerPorId(id));
    }

    // Actualizar palabra por ID
    @PutMapping("/{id}")
    public ResponseEntity<PalabraIncompletaResponse> actualizar(
            @PathVariable Long id,
            @RequestBody PalabraIncompletaRequest request) {
        return ResponseEntity.ok(palabraIncompletaService.actualizar(id, request));
    }

    // Eliminar palabra por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPorId(@PathVariable Long id) {
        palabraIncompletaService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }

    // Listar palabras por actividad
    @GetMapping("/actividad/{actividadId}")
    public ResponseEntity<List<PalabraIncompletaResponse>> listarPorActividad(@PathVariable Long actividadId) {
        return ResponseEntity.ok(palabraIncompletaService.listarPorActividadId(actividadId));
    }

    // Listar palabras por categoría
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<PalabraIncompletaResponse>> listarPorCategoria(@PathVariable String categoria) {
        return ResponseEntity.ok(palabraIncompletaService.listarPorCategoria(categoria));
    }

    // Buscar palabras por coincidencia (palabra completa)
    @GetMapping("/buscar")
    public ResponseEntity<List<PalabraIncompletaResponse>> buscarPorPalabra(@RequestParam String palabra) {
        return ResponseEntity.ok(palabraIncompletaService.buscarPorPalabra(palabra));
    }
}
