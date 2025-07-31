package com.runasimi_edu.backend.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.runasimi_edu.backend.dto.request.LogroRequest;
import com.runasimi_edu.backend.dto.response.LogroResponse;
import com.runasimi_edu.backend.model.Actividad;
import com.runasimi_edu.backend.model.Logro;
import com.runasimi_edu.backend.model.Usuario;
import com.runasimi_edu.backend.repository.ActividadRepository;
import com.runasimi_edu.backend.repository.LogroRepository;
import com.runasimi_edu.backend.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogroService {

    private final LogroRepository logroRepository;
    private final UsuarioRepository usuarioRepository;
    private final ActividadRepository actividadRepository;

    //private final SimpleDateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
    private final SimpleDateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    // Registrar
    public LogroResponse registrar(LogroRequest request) {
        Logro logro = new Logro();

        Usuario alumno = usuarioRepository.findById(request.getAlumnoId())
            .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));
        logro.setAlumno(alumno);

        if (request.getActividadId() != null) {
            Actividad actividad = actividadRepository.findById(request.getActividadId())
                .orElseThrow(() -> new RuntimeException("Actividad no encontrada"));
            logro.setActividad(actividad);
        }

        logro.setTipo(request.getTipo());
        logro.setCantidad(request.getCantidad());
        logro.setDescripcion(request.getDescripcion());
        logro.setFechaObtencion(parseFecha(request.getFechaObtencion()));

        Logro guardado = logroRepository.save(logro);
        return convertirADTO(guardado);
    }

    // Listar todos
    public List<LogroResponse> listarTodos() {
        return logroRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    // Buscar por ID
    public Optional<LogroResponse> buscarPorId(Long id) {
        return logroRepository.findById(id).map(this::convertirADTO);
    }

    // Listar por alumno
    public List<LogroResponse> listarPorAlumno(Long alumnoId) {
        Usuario alumno = usuarioRepository.findById(alumnoId)
            .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));
        return logroRepository.findByAlumno(alumno)
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    // Listar por alumno y tipo
    public List<LogroResponse> listarPorAlumnoYTipo(Long alumnoId, Logro.TipoLogro tipo) {
        return logroRepository.findByAlumnoIdAndTipo(alumnoId, tipo)
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    // Contar por alumno
    public int contarPorAlumno(Long alumnoId) {
        Usuario alumno = usuarioRepository.findById(alumnoId)
            .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));
        return logroRepository.countByAlumno(alumno);
    }

    // Actualizar
    public LogroResponse actualizar(Long id, LogroRequest request) {
        Logro logro = logroRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Logro no encontrado"));

        Usuario alumno = usuarioRepository.findById(request.getAlumnoId())
            .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));
        logro.setAlumno(alumno);

        if (request.getActividadId() != null) {
            Actividad actividad = actividadRepository.findById(request.getActividadId())
                .orElseThrow(() -> new RuntimeException("Actividad no encontrada"));
            logro.setActividad(actividad);
        } else {
            logro.setActividad(null);
        }

        logro.setTipo(request.getTipo());
        logro.setCantidad(request.getCantidad());
        logro.setDescripcion(request.getDescripcion());
        logro.setFechaObtencion(parseFecha(request.getFechaObtencion()));

        return convertirADTO(logroRepository.save(logro));
    }

    // Eliminar
    public void eliminar(Long id) {
        logroRepository.deleteById(id);
    }

    // Conversión de String ISO8601 a java.util.Date
      private Date parseFecha(String fecha) {
        try {
            return iso8601Format.parse(fecha);
        } catch (ParseException e) {
            throw new RuntimeException("Formato de fecha inválido. Se esperaba ISO 8601.", e);
        }
    }

    // Conversión a DTO de respuesta
    private LogroResponse convertirADTO(Logro logro) {
        return new LogroResponse(
                logro.getActividad() != null ? logro.getActividad().getId() : null,
                logro.getAlumno().getId(),
                logro.getCantidad(),
                logro.getDescripcion(),
                logro.getFechaObtencion(),
                logro.getId(),
                logro.getActividad() != null ? logro.getActividad().getNombre() : null,
                logro.getAlumno().getNombreCompleto(),
                logro.getTipo()
        );
    }
}
