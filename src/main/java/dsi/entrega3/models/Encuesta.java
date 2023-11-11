package dsi.entrega3.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Encuesta {
    private String descripcion;
    private LocalDateTime fechaFinVigencia;
    private ArrayList<Pregunta> preguntas;

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
