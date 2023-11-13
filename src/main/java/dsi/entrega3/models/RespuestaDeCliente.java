package dsi.entrega3.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "RespuestasDeCliente")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RespuestaDeCliente {

        // Atributos de la clase RespuestaDeCliente
        @Id
        @Column(name = "id")
        @GeneratedValue(generator = "RespuestasDeCliente")
        @TableGenerator(name = "RespuestasDeCliente", table = "sqlite_sequence",
                pkColumnName = "name", pkColumnValue = "RespuestasDeCliente", valueColumnName = "seq",
                initialValue = 1, allocationSize = 1)
        private Long id;

        @Column(name = "fechaEncuesta")
        private LocalDateTime fechaDeEncuesta;

        @OneToOne
        @JoinColumn(name = "id_respuesta_posible", nullable = false)
        private RespuestaPosible respuestaSeleccionada;

        @ManyToOne
        @JoinColumn(name = "id_llamada", nullable = false)
        private Llamada llamada;

         //Método que se utiliza en la implementación del CU
        public String getDescripcionRta()
        {
            return respuestaSeleccionada.getDescripcionRta();
        }
}
