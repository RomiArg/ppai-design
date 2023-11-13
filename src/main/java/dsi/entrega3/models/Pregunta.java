package dsi.entrega3.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Pregunta")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Pregunta {
    /* Atributos de la clase Pregunta */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "Pregunta")
    @TableGenerator(name = "Pregunta", table = "sqlite_sequence",
            pkColumnName = "name", pkColumnValue = "Pregunta", valueColumnName = "seq",
            initialValue = 1, allocationSize = 1)
    private Long id;

    private String pregunta;

    @ManyToOne
    @JoinColumn(name = "id_encuesta", nullable = false)
    private Encuesta encuesta;

    @OneToMany(mappedBy = "pregunta", fetch = FetchType.EAGER)
    private List<RespuestaPosible> respuestaPosibles;

    public Boolean esEncuestaDeCliente(List<RespuestaPosible> respuestas)
    {
        for (RespuestaPosible respuesta : respuestas)
        {
            if (tieneRtaPosible(respuesta))
            {
                return true;
            }
        }
        return false;
    }

    public Boolean tieneRtaPosible(RespuestaPosible respuesta)
    {
        for (RespuestaPosible respuestaPosible : respuestaPosibles)
        {
            if (respuesta.getDescripcion().equals(respuestaPosible.getDescripcionRta()))
            {
                return true;
            }
        }
        return false;
    }
}
