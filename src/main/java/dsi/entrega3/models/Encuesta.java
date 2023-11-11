package dsi.entrega3.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@Table(name = "Encuesta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Encuesta {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "Encuesta")
    @TableGenerator(name = "Encuesta", table = "sqlite_sequence",
            pkColumnName = "name", pkColumnValue = "Encuesta", valueColumnName = "seq",
            initialValue = 1, allocationSize = 1)
    private Long id;

    private String descripcion;
    private LocalDateTime fechaFinVigencia;

    @OneToMany(mappedBy = "encuesta", fetch = FetchType.LAZY)
    public ArrayList<Pregunta> preguntas;

    // Este método convierte a los atributos en string para mostrarlos
    public String mostrarDatos()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("Descripcion: ").append(descripcion);
        sb.append("Fecha Fin Vigencia: ").append(fechaFinVigencia.toString());
        for (Pregunta pta : preguntas)
        {
            sb.append(pta.mostrarDatos());
        }
        return sb.toString();
    }

    // Métodos que son utilizados en la implementación del CU
    public boolean esEncuestaDeCliente(ArrayList<RespuestaPosible> respuestas)
    {
        for (Pregunta pregunta : preguntas)
        {
            if (!pregunta.esEncuestaDeCliente(respuestas))
            {
                return false;
            }
        }
        return true;
    }

    public boolean esVigente(LocalDateTime fechaVig)
    {
        if(fechaFinVigencia.isBefore(fechaVig))
        {
            return true;
        }
        return false;
    }

}
