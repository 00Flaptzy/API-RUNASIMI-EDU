package com.runasimi_edu.backend.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.runasimi_edu.backend.model.Actividad.NivelDificultad;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "frases_ordenables")
public class FraseOrdenable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "actividad_id", nullable = false)
    private Actividad actividad;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String fraseCorrecta;

   @ElementCollection
@CollectionTable(
    name = "frase_ordenable_palabras",
    joinColumns = @JoinColumn(name = "frase_ordenable_id")
)
@Column(name = "palabra", nullable = false)
private List<String> palabrasDesordenadas;

    @Column(columnDefinition = "TEXT")
    private String traduccionEspanol;

    @Enumerated(EnumType.STRING)
    private NivelDificultad nivel;

    private String urlAudio;
    private String urlImagen;

    public FraseOrdenable() {
    }

    public FraseOrdenable(Actividad actividad, String fraseCorrecta, Long id, NivelDificultad nivel, List<String> palabrasDesordenadas, String traduccionEspanol, String urlAudio, String urlImagen) {
        this.actividad = actividad;
        this.fraseCorrecta = fraseCorrecta;
        this.id = id;
        this.nivel = nivel;
        this.palabrasDesordenadas = palabrasDesordenadas;
        this.traduccionEspanol = traduccionEspanol;
        this.urlAudio = urlAudio;
        this.urlImagen = urlImagen;
    }
}
