package dsi.entrega3.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespuestaPosible {
    /* Atributos de la clase RespuestaPosible */
    private String descripcion;
    private String valor;

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
