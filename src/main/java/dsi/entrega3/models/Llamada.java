package dsi.entrega3.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Llamada {

    // Atributos de la clase Llamada
    private String descripcionOperador;
    private String detalleAccionRequerida;
    private float duracion;
    private boolean encuestaEnviada;
    private String observacionAuditor;
    private ArrayList<RespuestaDeCliente> respuestasDeEncuesta;
    private Cliente cliente;
    private ArrayList<CambioEstado> cambiosEstado;
    // Este método convierte a los atributos en string para mostrarlos
    public String mostrarDatos()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Descripcion del operador:").append(descripcionOperador);
        sb.append("Detalle de Acción Requerida:").append(detalleAccionRequerida);
        sb.append("Datos del cliente:").append(cliente.MostrarDatos());
        sb.append("Encuesta:");
        sb.append("Observación del auditor:").append(observacionAuditor);
        sb.append("Estado:").append(getNombreClienteYEstado());

        return sb.toString();
    }
    public float calcularDuracion()
    {
        CambioEstado inicial = null;
        CambioEstado finalC = null;
        for (int i = 0; i < cambiosEstado.size(); i++)
        {
            if (cambiosEstado.get(i).esEstadoInicial())
            {
                inicial = cambiosEstado.get(i);
            }
            if (cambiosEstado.get(i).esEstadoFinal())
            {
                finalC = cambiosEstado.get(i);
            }
        }

        Duration duracion = Duration.between(inicial.getFechaHoraInicio(), finalC.getFechaHoraInicio());
        return duracion.toMinutes();
    }

    public boolean esDePeriodo(LocalDateTime fechaInicio, LocalDateTime fechaFin)
    {
        for(CambioEstado cambio : cambiosEstado)
        {
            if (cambio.esEstadoInicial() && (!cambio.getFechaHoraInicio().isBefore(fechaInicio) &&
                    !cambio.getFechaHoraInicio().isAfter(fechaFin)))
            {
                return true;
            }
        }
        return false;
    }

    public boolean tieneEncuestaRespondida()
    {
        if (respuestasDeEncuesta != null)
        {
            return true;
        }
        return false;
    }

    public ArrayList<String> getNombreClienteYEstado()
    {
        String nombreCompleto = cliente.getNombreCompleto();
        String estado = CambioEstado.esEstadoActual(cambiosEstado);
        ArrayList<String> lista = new ArrayList<String>();

        if (estado != null)
        {
            lista.add(nombreCompleto);
            lista.add(estado);
            return lista;
        }
        lista.add(nombreCompleto);
        lista.add("No se encontro estado actual");
        return lista;
    }

}
