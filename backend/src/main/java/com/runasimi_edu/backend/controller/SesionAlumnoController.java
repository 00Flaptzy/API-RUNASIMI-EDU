package com.runasimi_edu.backend.controller;

import java.util.Date;
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

import com.runasimi_edu.backend.dto.response.SesionAlumnoResponse;
import com.runasimi_edu.backend.model.SesionAlumno;
import com.runasimi_edu.backend.service.SesionAlumnoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/sesiones")
@RequiredArgsConstructor
public class SesionAlumnoController {

    private final SesionAlumnoService sesionAlumnoService;

    // Crear una nueva sesión
    @PostMapping
    public ResponseEntity<SesionAlumnoResponse> crearSesion(@RequestBody SesionAlumno sesion) {
        return ResponseEntity.ok(sesionAlumnoService.guardarSesion(sesion));
    }

    // Actualizar sesión solo una vez
    @PutMapping("/{id}")
    public ResponseEntity<SesionAlumnoResponse> actualizarSesion(
            @PathVariable Long id,
            @RequestBody SesionAlumno nuevosDatos) {
        return ResponseEntity.ok(sesionAlumnoService.actualizarSesion(id, nuevosDatos));
    }

    // Listar todas las sesiones
    @GetMapping
    public ResponseEntity<List<SesionAlumnoResponse>> listarTodas() {
        return ResponseEntity.ok(sesionAlumnoService.listarTodas());
    }

    // Listar sesiones por alumno ID
    @GetMapping("/alumno/{alumnoId}")
    public ResponseEntity<List<SesionAlumnoResponse>> listarPorAlumno(@PathVariable Long alumnoId) {
        return ResponseEntity.ok(sesionAlumnoService.listarPorAlumno(alumnoId));
    }

    // Listar por alumno y completado
    @GetMapping("/alumno/{alumnoId}/completado/{completado}")
    public ResponseEntity<List<SesionAlumnoResponse>> listarPorAlumnoYCompletado(
            @PathVariable Long alumnoId,
            @PathVariable Boolean completado) {
        return ResponseEntity.ok(sesionAlumnoService.listarPorAlumnoYCompletado(alumnoId, completado));
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<SesionAlumnoResponse> obtenerPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(sesionAlumnoService.buscarPorId(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        sesionAlumnoService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }

    // Buscar sesiones entre fechas (query params)
    @GetMapping("/rango")
    public ResponseEntity<List<SesionAlumnoResponse>> buscarPorRangoFechas(
            @RequestParam Date inicio,
            @RequestParam Date fin) {
        return ResponseEntity.ok(sesionAlumnoService.buscarPorRangoFechas(inicio, fin));
    }

    // Obtener puntaje total de un alumno
    @GetMapping("/alumno/{alumnoId}/puntaje")
    public ResponseEntity<Integer> puntajeTotal(@PathVariable Long alumnoId) {
        return ResponseEntity.ok(sesionAlumnoService.obtenerPuntajeTotalPorAlumno(alumnoId));
    }
}
