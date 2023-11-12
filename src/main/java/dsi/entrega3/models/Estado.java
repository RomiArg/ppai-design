package dsi.entrega3.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Estado")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Estado {
    /* Atributos de la clase Estado */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "Estado")
    @TableGenerator(name = "Estado", table = "sqlite_sequence",
            pkColumnName = "name", pkColumnValue = "Estado", valueColumnName = "seq",
            initialValue = 1, allocationSize = 1)
    private Long id;

    private String nombre;

    /* Este método convierte al atributo en string para mostrarlo */
    public String MostrarDatos()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("Nombre:").append(nombre);

        return sb.toString();
    }

    /* Métodos que son utilizados en la implementación del CU */
    public Boolean EsFinalizada()
    {
        return nombre.equalsIgnoreCase("FINALIZADA");
    }

    public Boolean esIniciada()
    {
        if (nombre.equalsIgnoreCase("INICIADA"))
        {
            return true;
        }
        return false;
    }
}
