package com.runasimi_edu.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.runasimi_edu.backend.dto.request.LogroRequest;
import com.runasimi_edu.backend.dto.response.LogroResponse;
import com.runasimi_edu.backend.model.Logro.TipoLogro;
import com.runasimi_edu.backend.service.LogroService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/logros")
@RequiredArgsConstructor
public class LogroController {

    private final LogroService logroService;

    // Registrar logro
    @PostMapping
    public ResponseEntity<LogroResponse> registrar(@RequestBody LogroRequest request) {
        LogroResponse response = logroService.registrar(request);
        return ResponseEntity.ok(response);
    }

    // Listar todos los logros
    @GetMapping
    public ResponseEntity<List<LogroResponse>> listarTodos() {
        return ResponseEntity.ok(logroService.listarTodos());
    }

    // Obtener logro por ID
    @GetMapping("/{id}")
    public ResponseEntity<LogroResponse> obtenerPorId(@PathVariable Long id) {
        Optional<LogroResponse> response = logroService.buscarPorId(id);
        return response.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Listar logros por ID de alumno
    @GetMapping("/alumno/{alumnoId}")
    public ResponseEntity<List<LogroResponse>> listarPorAlumno(@PathVariable Long alumnoId) {
        return ResponseEntity.ok(logroService.listarPorAlumno(alumnoId));
    }

    // Listar logros por alumno y tipo
    @GetMapping("/alumno/{alumnoId}/tipo/{tipo}")
    public ResponseEntity<List<LogroResponse>> listarPorAlumnoYTipo(
            @PathVariable Long alumnoId,
            @PathVariable TipoLogro tipo) {
        return ResponseEntity.ok(logroService.listarPorAlumnoYTipo(alumnoId, tipo));
    }

    // Contar logros por alumno
    @GetMapping("/alumno/{alumnoId}/contar")
    public ResponseEntity<Integer> contarPorAlumno(@PathVariable Long alumnoId) {
        return ResponseEntity.ok(logroService.contarPorAlumno(alumnoId));
    }

    // Actualizar logro
    @PutMapping("/{id}")
    public ResponseEntity<LogroResponse> actualizar(
            @PathVariable Long id,
            @RequestBody LogroRequest request) {
        LogroResponse actualizado = logroService.actualizar(id, request);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar logro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        logroService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
