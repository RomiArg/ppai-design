package dsi.entrega3.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Pregunta")
@Data
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

    @OneToMany(mappedBy = "pregunta", fetch = FetchType.LAZY)
    private ArrayList<RespuestaPosible> respuestaPosibles;

    /* Este método convierte a los atributos en string para mostrarlos */
    public String mostrarDatos()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(pregunta);
        for (RespuestaPosible rta: respuestaPosibles) {
            sb.append(rta.mostrarDatos());
        }
        return sb.toString();
    }

    /* Métodos que son utilizados en la implementación del CU */
    public String listarRespuestasPosibles()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Respuestas Posibles: ");
        for (RespuestaPosible respuestaPosible : respuestaPosibles)
        {
            sb.append(respuestaPosible.mostrarDatos());
            sb.append("");
        }
        return sb.toString();
    }

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
            if (respuesta.getDescripcion() == respuestaPosible.getDescripcionRta())
            {
                return true;
            }
        }
        return false;
    }
}
