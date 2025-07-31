package com.runasimi_edu.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.runasimi_edu.backend.dto.request.FraseOrdenableRequest;
import com.runasimi_edu.backend.model.Actividad.NivelDificultad;
import com.runasimi_edu.backend.model.FraseOrdenable;
import com.runasimi_edu.backend.service.FraseOrdenableService;

@RestController
@RequestMapping("/api/frases-ordenables")
@CrossOrigin(origins = "*")
public class FraseOrdenableController {

    @Autowired
    private FraseOrdenableService fraseOrdenableService;

    @PostMapping
    public ResponseEntity<FraseOrdenable> guardar(@RequestBody FraseOrdenableRequest request) {
        try {
            FraseOrdenable frase = fraseOrdenableService.guardarDesdeDto(request);
            return ResponseEntity.ok(frase);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<FraseOrdenable> obtenerPorId(@PathVariable Long id) {
        return fraseOrdenableService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            fraseOrdenableService.eliminarPorId(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<FraseOrdenable>> listarTodas() {
        return ResponseEntity.ok(fraseOrdenableService.listarTodas());
    }

    @GetMapping("/actividad/{actividadId}")
    public ResponseEntity<List<FraseOrdenable>> buscarPorActividadId(@PathVariable Long actividadId) {
        return ResponseEntity.ok(fraseOrdenableService.buscarPorActividadId(actividadId));
    }

    @GetMapping("/nivel/{nivel}")
    public ResponseEntity<List<FraseOrdenable>> buscarPorNivel(@PathVariable String nivel) {
        try {
            NivelDificultad dificultad = NivelDificultad.valueOf(nivel.toUpperCase());
            return ResponseEntity.ok(fraseOrdenableService.buscarPorNivel(dificultad));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
