package dsi.entrega3.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    /* Atributos de la clase Cliente */
    private String nombreCompleto;
    private int dni;
    private int nroCelular;

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
