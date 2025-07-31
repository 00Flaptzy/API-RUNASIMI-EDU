package com.runasimi_edu.backend.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.runasimi_edu.backend.dto.response.SesionAlumnoResponse;
import com.runasimi_edu.backend.model.Actividad;
import com.runasimi_edu.backend.model.SesionAlumno;
import com.runasimi_edu.backend.model.Usuario;
import com.runasimi_edu.backend.repository.SesionAlumnoRepository;
import com.runasimi_edu.backend.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SesionAlumnoService {

    private final SesionAlumnoRepository sesionAlumnoRepository;
    private final UsuarioRepository usuarioRepository;

    // Guardar una nueva sesión
    public SesionAlumnoResponse guardarSesion(SesionAlumno sesion) {
        if (sesion == null || sesion.getAlumno() == null || sesion.getActividad() == null) {
            throw new IllegalArgumentException("Sesión, alumno o actividad no pueden ser nulos.");
        }

        boolean yaExiste = sesionAlumnoRepository.existsByAlumnoAndActividad(
            sesion.getAlumno(), sesion.getActividad()
        );

        if (yaExiste) {
            throw new IllegalStateException("El alumno ya tiene una sesión registrada para esta actividad.");
        }

        SesionAlumno guardada = sesionAlumnoRepository.save(sesion);
        return mapearASesionAlumnoResponse(guardada);
    }

    // Actualizar una sesión existente
    @Transactional
    public SesionAlumnoResponse actualizarSesion(Long sesionId, SesionAlumno nuevosDatos) {
        SesionAlumno existente = sesionAlumnoRepository.findById(sesionId)
            .orElseThrow(() -> new IllegalArgumentException("Sesión no encontrada con ID: " + sesionId));

        if (Boolean.TRUE.equals(existente.getCompletado())) {
            throw new IllegalStateException("Esta sesión ya fue completada y no puede ser modificada.");
        }

        existente.setFechaFin(nuevosDatos.getFechaFin());
        existente.setPuntosObtenidos(nuevosDatos.getPuntosObtenidos());
        existente.setIntentos(nuevosDatos.getIntentos());
        existente.setTiempoSegundos(nuevosDatos.getTiempoSegundos());
        existente.setEstado(SesionAlumno.EstadoSesion.COMPLETADO);
        existente.setCompletado(true);
        existente.setUltimoItem(nuevosDatos.getUltimoItem());
        existente.setDetalles(nuevosDatos.getDetalles());

        SesionAlumno actualizada = sesionAlumnoRepository.save(existente);
        return mapearASesionAlumnoResponse(actualizada);
    }

    // Listar todas las sesiones en formato DTO
    public List<SesionAlumnoResponse> listarTodas() {
        return sesionAlumnoRepository.findAll().stream()
            .map(this::mapearASesionAlumnoResponse)
            .toList();
    }

    // Listar sesiones por alumno en formato DTO
    public List<SesionAlumnoResponse> listarPorAlumno(Long alumnoId) {
        Usuario alumno = usuarioRepository.findById(alumnoId)
            .orElseThrow(() -> new IllegalArgumentException("Alumno no encontrado con ID: " + alumnoId));
        return sesionAlumnoRepository.findByAlumno(alumno).stream()
            .map(this::mapearASesionAlumnoResponse)
            .toList();
    }

    public List<SesionAlumnoResponse> listarPorActividad(Actividad actividad) {
        if (actividad == null) {
            throw new IllegalArgumentException("La actividad no puede ser nula.");
        }
        return sesionAlumnoRepository.findByActividad(actividad).stream()
            .map(this::mapearASesionAlumnoResponse)
            .toList();
    }

    public List<SesionAlumnoResponse> listarPorAlumnoYCompletado(Long alumnoId, Boolean completado) {
        Usuario alumno = usuarioRepository.findById(alumnoId)
            .orElseThrow(() -> new IllegalArgumentException("Alumno no encontrado con ID: " + alumnoId));
        return sesionAlumnoRepository.findByAlumnoAndCompletado(alumno, completado).stream()
            .map(this::mapearASesionAlumnoResponse)
            .toList();
    }

    public SesionAlumnoResponse buscarPorId(Long id) {
        SesionAlumno sesion = sesionAlumnoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Sesión no encontrada con ID: " + id));
        return mapearASesionAlumnoResponse(sesion);
    }

    public void eliminarPorId(Long id) {
        if (!sesionAlumnoRepository.existsById(id)) {
            throw new IllegalArgumentException("No existe la sesión con ID: " + id);
        }
        sesionAlumnoRepository.deleteById(id);
    }

    public List<SesionAlumnoResponse> buscarPorRangoFechas(Date inicio, Date fin) {
        if (inicio == null || fin == null) {
            throw new IllegalArgumentException("Las fechas no pueden ser nulas.");
        }
        return sesionAlumnoRepository.findByFechaInicioBetween(inicio, fin).stream()
            .map(this::mapearASesionAlumnoResponse)
            .toList();
    }

    public int contarCompletadasPorActividad(Actividad actividad) {
        if (actividad == null) {
            throw new IllegalArgumentException("La actividad no puede ser nula.");
        }
        return sesionAlumnoRepository.countByActividadAndCompletado(actividad, true);
    }

    public Integer obtenerPuntajeTotalPorAlumno(Long alumnoId) {
        Usuario alumno = usuarioRepository.findById(alumnoId)
            .orElseThrow(() -> new IllegalArgumentException("Alumno no encontrado con ID: " + alumnoId));
        Integer suma = sesionAlumnoRepository.sumPuntosObtenidosByAlumno(alumno);
        return (suma != null) ? suma : 0;
    }

    // === Mapper interno ===
    private SesionAlumnoResponse mapearASesionAlumnoResponse(SesionAlumno sesion) {
        return new SesionAlumnoResponse(
            sesion.getActividad().getId(),
            sesion.getActividad().getNombre(),
            sesion.getAlumno().getId(),
            sesion.getAlumno().getNombreCompleto(),
            sesion.getCompletado(),
            sesion.getDetalles(),
            sesion.getEstado(),
            sesion.getFechaFin(),
            sesion.getFechaInicio(),
            sesion.getId(),
            sesion.getIntentos(),
            sesion.getPuntosObtenidos(),
            sesion.getTiempoSegundos(),
            sesion.getUltimoItem()
        );
    }
}
