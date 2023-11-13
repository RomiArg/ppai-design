package dsi.entrega3.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "RespuestaPosible")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RespuestaPosible {
    /* Atributos de la clase RespuestaPosible */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "RespuestaPosible")
    @TableGenerator(name = "RespuestaPosible", table = "sqlite_sequence",
            pkColumnName = "name", pkColumnValue = "RespuestaPosible", valueColumnName = "seq",
            initialValue = 1, allocationSize = 1)
    private Long id;

    private String descripcion;
    private int valor;

    @ManyToOne
    @JoinColumn(name = "id_pregunta", nullable = false)
    private Pregunta pregunta;

    /* Método que se utiliza en la implementación del CU */
    public String getDescripcionRta() { return descripcion; }

}
