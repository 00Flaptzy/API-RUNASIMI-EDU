package com.runasimi_edu.backend.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.runasimi_edu.backend.dto.request.ReporteEstadisticoRequestDTO;
import com.runasimi_edu.backend.dto.response.ReporteEstadisticoResponseDTO;
import com.runasimi_edu.backend.model.Grado;
import com.runasimi_edu.backend.model.ReporteEstadistico;
import com.runasimi_edu.backend.model.Usuario;
import com.runasimi_edu.backend.repository.GradoRepository;
import com.runasimi_edu.backend.repository.ReporteEstadisticoRepository;
import com.runasimi_edu.backend.repository.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReporteEstadisticoService {

    private final ReporteEstadisticoRepository reporteRepository;
    private final UsuarioRepository usuarioRepository;
    private final GradoRepository gradoRepository;

    public ReporteEstadisticoResponseDTO guardar(ReporteEstadisticoRequestDTO dto) {
        Usuario docente = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario (docente) no encontrado"));

        Grado grado = gradoRepository.findById(dto.getGradoId())
                .orElseThrow(() -> new EntityNotFoundException("Grado no encontrado"));

        ReporteEstadistico reporte = new ReporteEstadistico();
        reporte.setDocente(docente);
        reporte.setGrado(grado);
        reporte.setRangoFechas(dto.getRangoFechas());
        reporte.setTipoReporte(dto.getTipoReporte());
        reporte.setDatos(dto.getDatos());
        reporte.setCompartidoCon(dto.getCompartidoCon());

        ReporteEstadistico guardado = reporteRepository.save(reporte);
        return mapToResponse(guardado);
    }

    public List<ReporteEstadisticoResponseDTO> listarPorDocente(Long usuarioId) {
        Usuario docente = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Docente no encontrado"));
        return reporteRepository.findByDocente(docente).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<ReporteEstadisticoResponseDTO> listarPorTipo(ReporteEstadistico.TipoReporte tipo) {
        return reporteRepository.findByTipoReporte(tipo).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<ReporteEstadisticoResponseDTO> listarEntreFechas(Date inicio, Date fin) {
        return reporteRepository.findByFechaGeneracionBetween(inicio, fin).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<ReporteEstadisticoResponseDTO> listarPorDocenteYTipo(Long usuarioId, ReporteEstadistico.TipoReporte tipo) {
        Usuario docente = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Docente no encontrado"));
        return reporteRepository.findByDocenteAndTipoReporte(docente, tipo).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private ReporteEstadisticoResponseDTO mapToResponse(ReporteEstadistico reporte) {
        ReporteEstadisticoResponseDTO dto = new ReporteEstadisticoResponseDTO();
        dto.setId(reporte.getId());
        dto.setDocenteId(reporte.getDocente().getId());
        dto.setNombreDocente(reporte.getDocente().getNombreCompleto());
        dto.setGradoId(reporte.getGrado().getId());
        dto.setNombreGrado(reporte.getGrado().getNombre());
        dto.setFechaGeneracion(reporte.getFechaGeneracion());
        dto.setRangoFechas(reporte.getRangoFechas());
        dto.setTipoReporte(reporte.getTipoReporte());
        dto.setDatos(reporte.getDatos());
        dto.setCompartidoCon(reporte.getCompartidoCon());
        return dto;
    }
}
