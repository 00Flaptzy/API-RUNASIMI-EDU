package com.runasimi_edu.backend.controller;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.runasimi_edu.backend.dto.request.ReporteEstadisticoRequestDTO;
import com.runasimi_edu.backend.dto.response.ReporteEstadisticoResponseDTO;
import com.runasimi_edu.backend.model.ReporteEstadistico.TipoReporte;
import com.runasimi_edu.backend.service.ReporteEstadisticoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
public class ReporteEstadisticoController {

    private final ReporteEstadisticoService reporteService;

    // Crear un nuevo reporte
    @PostMapping
    public ResponseEntity<ReporteEstadisticoResponseDTO> crearReporte(
            @RequestBody ReporteEstadisticoRequestDTO requestDTO) {
        return ResponseEntity.ok(reporteService.guardar(requestDTO));
    }

    // Listar todos los reportes por docente (usuario)
    @GetMapping("/docente/{usuarioId}")
    public ResponseEntity<List<ReporteEstadisticoResponseDTO>> listarPorDocente(
            @PathVariable Long usuarioId) {
        return ResponseEntity.ok(reporteService.listarPorDocente(usuarioId));
    }

    // Listar reportes por tipo (AVANCE, RENDIMIENTO, etc.)
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<ReporteEstadisticoResponseDTO>> listarPorTipo(
            @PathVariable TipoReporte tipo) {
        return ResponseEntity.ok(reporteService.listarPorTipo(tipo));
    }

    // Listar reportes por rango de fechas
    @GetMapping("/fechas")
    public ResponseEntity<List<ReporteEstadisticoResponseDTO>> listarEntreFechas(
            @RequestParam("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date inicio,
            @RequestParam("fin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fin) {
        return ResponseEntity.ok(reporteService.listarEntreFechas(inicio, fin));
    }

    // Listar reportes por docente y tipo
    @GetMapping("/docente/{usuarioId}/tipo/{tipo}")
    public ResponseEntity<List<ReporteEstadisticoResponseDTO>> listarPorDocenteYTipo(
            @PathVariable Long usuarioId,
            @PathVariable TipoReporte tipo) {
        return ResponseEntity.ok(reporteService.listarPorDocenteYTipo(usuarioId, tipo));
    }
}
