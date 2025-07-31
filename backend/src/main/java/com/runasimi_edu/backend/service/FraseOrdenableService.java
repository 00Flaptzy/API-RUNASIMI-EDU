package com.runasimi_edu.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runasimi_edu.backend.dto.request.FraseOrdenableRequest;
import com.runasimi_edu.backend.model.Actividad;
import com.runasimi_edu.backend.model.Actividad.NivelDificultad;
import com.runasimi_edu.backend.model.FraseOrdenable;
import com.runasimi_edu.backend.repository.ActividadRepository;
import com.runasimi_edu.backend.repository.FraseOrdenableRepository;

@Service
public class FraseOrdenableService {

    @Autowired
    private FraseOrdenableRepository fraseOrdenableRepository;

    @Autowired
    private ActividadRepository actividadRepository;

   public FraseOrdenable guardarDesdeDto(FraseOrdenableRequest request) {
    Actividad actividad = actividadRepository.findById(request.getActividadId())
            .orElseThrow(() -> new RuntimeException("Actividad no encontrada"));

    FraseOrdenable frase = new FraseOrdenable();
    frase.setActividad(actividad);
    frase.setFraseCorrecta(request.getFraseCorrecta());
    frase.setPalabrasDesordenadas(request.getPalabrasDesordenadas()); // Ya no convertimos a String
    frase.setTraduccionEspanol(request.getTraduccionEspanol());
    frase.setNivel(request.getNivel());
    frase.setUrlAudio(request.getUrlAudio());
    frase.setUrlImagen(request.getUrlImagen());

    return fraseOrdenableRepository.save(frase);
}

    // Crear desde DTO
    public FraseOrdenable crearFraseOrdenable(FraseOrdenableRequest request) {
        Actividad actividad = actividadRepository.findById(request.getActividadId())
                .orElseThrow(() -> new IllegalArgumentException("Actividad no encontrada con ID: " + request.getActividadId()));

        FraseOrdenable frase = new FraseOrdenable();
        frase.setActividad(actividad);
        frase.setFraseCorrecta(request.getFraseCorrecta());
        frase.setPalabrasDesordenadas(request.getPalabrasDesordenadas());
        frase.setTraduccionEspanol(request.getTraduccionEspanol());
        frase.setNivel(request.getNivel());
        frase.setUrlAudio(request.getUrlAudio());
        frase.setUrlImagen(request.getUrlImagen());

        return fraseOrdenableRepository.save(frase);
    }

    // Guardar o actualizar directamente (sin DTO)
    public FraseOrdenable guardar(FraseOrdenable frase) {
        return fraseOrdenableRepository.save(frase);
    }

    // Buscar por ID
    public Optional<FraseOrdenable> buscarPorId(Long id) {
        return fraseOrdenableRepository.findById(id);
    }

    // Eliminar por ID con validación
    public void eliminarPorId(Long id) {
        if (!fraseOrdenableRepository.existsById(id)) {
            throw new IllegalArgumentException("No existe frase ordenable con ID: " + id);
        }
        fraseOrdenableRepository.deleteById(id);
    }

    // Listar todas
    public List<FraseOrdenable> listarTodas() {
        return fraseOrdenableRepository.findAll();
    }

    // Buscar por actividad (entidad)
    public List<FraseOrdenable> buscarPorActividad(Actividad actividad) {
        return fraseOrdenableRepository.findByActividad(actividad);
    }

    // Buscar por ID de actividad
    public List<FraseOrdenable> buscarPorActividadId(Long actividadId) {
        return fraseOrdenableRepository.findByActividadId(actividadId);
    }

    // Buscar por nivel de dificultad
    public List<FraseOrdenable> buscarPorNivel(NivelDificultad nivel) {
        return fraseOrdenableRepository.findByNivel(nivel);
    }
}
