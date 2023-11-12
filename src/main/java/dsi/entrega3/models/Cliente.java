package dsi.entrega3.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Cliente")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    /* Atributos de la clase Cliente */
    @Id
    @Column(name = "dni")
    @GeneratedValue(generator = "Cliente")
    @TableGenerator(name = "Cliente", table = "sqlite_sequence",
            pkColumnName = "name", pkColumnValue = "Cliente", valueColumnName = "seq",
            initialValue = 1, allocationSize = 1)
    private int dni;

    @Column(name = "nombre")
    private String nombreCompleto;

    @Column(name = "nrocelular")
    private String nroCelular;

    /* Este método convierte a los atributos en string para mostrarlos */
    public String MostrarDatos()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("Nombre y Apellido:").append(nombreCompleto);
        sb.append("DNI:").append(dni);
        sb.append("Teléfono:").append(nroCelular);

        return sb.toString();

    }

}
