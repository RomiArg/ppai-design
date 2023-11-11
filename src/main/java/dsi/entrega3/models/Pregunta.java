package dsi.entrega3.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.lang.model.type.ArrayType;
import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pregunta {
    /* Atributos de la clase Pregunta */
    private String pregunta;
    private ArrayList<RespuestaPosible> respuestaPosibles;

    public List<RespuestaPosible> getRespuestaPosibles() {
        return respuestaPosibles;
    }

    public void setRespuestaPosibles(ArrayList<RespuestaPosible> respuestaPosibles) {
        this.respuestaPosibles = respuestaPosibles;
    }

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
