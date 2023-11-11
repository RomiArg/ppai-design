package dsi.entrega3.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CambioEstado {
    /* Atributos de la clase CambioEstado */
    private LocalDateTime fechaHoraInicio;
    private Estado estado;

    /* Este método convierte a los atributos en string para mostrarlos */
    public String mostrarDatos()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("Estado: ").append(estado.MostrarDatos());
        sb.append("\nFecha Hora Inicio: ").append(fechaHoraInicio);

        return sb.toString();
    }

    /* Métodos que son utilizados en la implementación del CU */
    public boolean esEstadoInicial()
    {
        return estado.EsIniciada();
    }

    public boolean esEstadoFinal()
    {
        if (estado.getNombre() == "Finalizada")
        {
            return true;
        }
        return false;
    }

    public String getNombreEstado()
    {
        return estado.getNombre();
    }


    //Este método buscar entre una lista de cambios de estado el estado con el atributo fechaHoraInicio mas grande,
    //primero ordenando la lista de mayor a menor y tomando el primer resultado
    public static String esEstadoActual(List<CambioEstado> cambiosEstado)
    {
        // Ordenar la lista en orden descendente según fechaHoraInicio
        Collections.sort(cambiosEstado, Comparator.comparing(CambioEstado::getFechaHoraInicio).reversed());

        // Tomar el primer resultado (si la lista no está vacía)
        if (!cambiosEstado.isEmpty()) {
            return cambiosEstado.get(0).getNombreEstado();
        } else {
            return null; // O manejar el caso de una lista vacía según tus necesidades
        }
    }
}
