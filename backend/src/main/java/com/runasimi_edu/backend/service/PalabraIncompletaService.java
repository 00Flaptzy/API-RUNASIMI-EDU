package com.runasimi_edu.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.runasimi_edu.backend.dto.request.PalabraIncompletaRequest;
import com.runasimi_edu.backend.dto.response.PalabraIncompletaResponse;
import com.runasimi_edu.backend.model.Actividad;
import com.runasimi_edu.backend.model.PalabraIncompleta;
import com.runasimi_edu.backend.repository.ActividadRepository;
import com.runasimi_edu.backend.repository.PalabraIncompletaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PalabraIncompletaService {

    private final PalabraIncompletaRepository palabraIncompletaRepository;
    private final ActividadRepository actividadRepository;

    // Crear una nueva palabra incompleta
    public PalabraIncompletaResponse crear(PalabraIncompletaRequest request) {
        Actividad actividad = actividadRepository.findById(request.getActividadId())
                .orElseThrow(() -> new RuntimeException("Actividad no encontrada con ID: " + request.getActividadId()));

        PalabraIncompleta palabra = new PalabraIncompleta();
        palabra.setActividad(actividad);
        palabra.setPalabraCompleta(request.getPalabraCompleta());
        palabra.setPalabraIncompleta(request.getPalabraIncompleta());
        palabra.setCategoriaVocabulario(request.getCategoriaVocabulario());
        palabra.setRutaAudio(request.getRutaAudio());
        palabra.setRutaImagen(request.getRutaImagen());

        PalabraIncompleta guardada = palabraIncompletaRepository.save(palabra);
        return mapearAResponse(guardada);
    }

    // Obtener todas las palabras
    public List<PalabraIncompletaResponse> listarTodas() {
        return palabraIncompletaRepository.findAll().stream()
                .map(this::mapearAResponse)
                .collect(Collectors.toList());
    }

    // Obtener por ID
    public PalabraIncompletaResponse obtenerPorId(Long id) {
        PalabraIncompleta palabra = palabraIncompletaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Palabra no encontrada con ID: " + id));
        return mapearAResponse(palabra);
    }

    // Eliminar por ID
    public void eliminarPorId(Long id) {
        if (!palabraIncompletaRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. Palabra con ID " + id + " no existe.");
        }
        palabraIncompletaRepository.deleteById(id);
    }

    // Actualizar palabra existente
    public PalabraIncompletaResponse actualizar(Long id, PalabraIncompletaRequest request) {
        PalabraIncompleta palabra = palabraIncompletaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Palabra no encontrada con ID: " + id));

        Actividad actividad = actividadRepository.findById(request.getActividadId())
                .orElseThrow(() -> new RuntimeException("Actividad no encontrada con ID: " + request.getActividadId()));

        palabra.setActividad(actividad);
        palabra.setPalabraCompleta(request.getPalabraCompleta());
        palabra.setPalabraIncompleta(request.getPalabraIncompleta());
        palabra.setCategoriaVocabulario(request.getCategoriaVocabulario());
        palabra.setRutaAudio(request.getRutaAudio());
        palabra.setRutaImagen(request.getRutaImagen());

        PalabraIncompleta actualizada = palabraIncompletaRepository.save(palabra);
        return mapearAResponse(actualizada);
    }

    // Listar por actividad ID
    public List<PalabraIncompletaResponse> listarPorActividadId(Long actividadId) {
        return palabraIncompletaRepository.findByActividadId(actividadId).stream()
                .map(this::mapearAResponse)
                .collect(Collectors.toList());
    }

    // Listar por categoría
    public List<PalabraIncompletaResponse> listarPorCategoria(String categoria) {
        return palabraIncompletaRepository.findByCategoriaVocabulario(categoria).stream()
                .map(this::mapearAResponse)
                .collect(Collectors.toList());
    }

    // Buscar por coincidencia de palabra
    public List<PalabraIncompletaResponse> buscarPorPalabra(String palabraClave) {
        return palabraIncompletaRepository.findByPalabraCompletaContainingIgnoreCase(palabraClave).stream()
                .map(this::mapearAResponse)
                .collect(Collectors.toList());
    }

    // Método privado para convertir entidad a DTO
    private PalabraIncompletaResponse mapearAResponse(PalabraIncompleta palabra) {
        PalabraIncompletaResponse response = new PalabraIncompletaResponse();
        response.setId(palabra.getId());
        response.setPalabraCompleta(palabra.getPalabraCompleta());
        response.setPalabraIncompleta(palabra.getPalabraIncompleta());
        response.setCategoriaVocabulario(palabra.getCategoriaVocabulario());
        response.setRutaAudio(palabra.getRutaAudio());
        response.setRutaImagen(palabra.getRutaImagen());

        if (palabra.getActividad() != null) {
            response.setNombreActividad(palabra.getActividad().getNombre());
        }

        return response;
    }
}
