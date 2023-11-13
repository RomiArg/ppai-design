package dsi.entrega3.models;

import dsi.entrega3.models.interfaces.IAgregado;
import dsi.entrega3.models.interfaces.Iterador;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Encuesta")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Encuesta implements IAgregado {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "Encuesta")
    @TableGenerator(name = "Encuesta", table = "sqlite_sequence",
            pkColumnName = "name", pkColumnValue = "Encuesta", valueColumnName = "seq",
            initialValue = 1, allocationSize = 1)
    private Long id;

    private String descripcion;
    private LocalDateTime fechaFinVigencia;

    @OneToMany(mappedBy = "encuesta", fetch = FetchType.EAGER)
    private List<Pregunta> preguntas;


    // Métodos que son utilizados en la implementación del CU
    public boolean esEncuestaDeCliente(List<RespuestaPosible> respuestas)
    {
        IteradorPreguntaImpl iterador = (IteradorPreguntaImpl) crearIterador(new ArrayList<>(this.preguntas));
        iterador.primero();
        while (!iterador.haTerminado())
        {
            Pregunta pregunta = iterador.actual();
            if (!iterador.cumpleFiltro(respuestas))
            {return false;}
            iterador.siguiente();
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

    @Override
    public Iterador crearIterador(List<Object> elementos) {
        if (!elementos.isEmpty()) {
            Class<?> tipoElemento = elementos.get(0).getClass();

            if (tipoElemento.equals(Pregunta.class)) {
                return new IteradorPreguntaImpl(elementos);
            }
        }
        throw new IllegalArgumentException("Tipo de elemento no compatible.");
    }
}
