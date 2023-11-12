package dsi.entrega3.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Llamada")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Llamada {
    // Atributos de la clase Llamada
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "Llamada")
    @TableGenerator(name = "Llamada", table = "sqlite_sequence",
            pkColumnName = "name", pkColumnValue = "Llamada", valueColumnName = "seq",
            initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "descripcionOperador")
    private String descripcionOperador;

    @Column(name = "detalleAccionRequerida")
    private String detalleAccionRequerida;

    @Column(name = "duracion")
    private float duracion;

    @Column(name = "encuestaEnviada")
    private int encuestaEnviada;

    @Column(name = "observacionAuditor")
    private String observacionAuditor;

    @OneToOne
    @JoinColumn(name = "dni_cliente", nullable = false)
    private Cliente cliente;

    @JsonIgnore
    @OneToMany(mappedBy = "llamada", fetch = FetchType.EAGER)
    private List<RespuestaDeCliente> respuestasDeEncuesta;

    @JsonIgnore
    @OneToMany(mappedBy = "llamada", fetch = FetchType.EAGER)
    private List<CambioEstado> cambiosEstado;

    // Este método convierte a los atributos en string para mostrarlos
    /*public String mostrarDatos()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Descripcion del operador:").append(descripcionOperador);
        sb.append("Detalle de Acción Requerida:").append(detalleAccionRequerida);
        sb.append("Datos del cliente:").append(cliente.MostrarDatos());
        sb.append("Encuesta:");
        sb.append("Observación del auditor:").append(observacionAuditor);
        sb.append("Estado:").append(getNombreClienteYEstado());

        return sb.toString();
    }*/
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

    public List<String> getNombreClienteYEstado()
    {
        String nombreCompleto = cliente.getNombreCompleto();
        String estado = CambioEstado.esEstadoActual(cambiosEstado);
        List<String> lista = new ArrayList<>();

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
