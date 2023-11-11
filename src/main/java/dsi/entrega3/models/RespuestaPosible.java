package dsi.entrega3.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "RespuestaPosible")
@Data
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

    /* Este método convierte a los atributos en string para mostrarlos */
    public String mostrarDatos()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("Descripcion: ").append(descripcion);
        sb.append("valor").append(valor);

        return sb.toString();
    }

    /* Método que se utiliza en la implementación del CU */
    public String getDescripcionRta()
    {
        return descripcion;
    }

}
