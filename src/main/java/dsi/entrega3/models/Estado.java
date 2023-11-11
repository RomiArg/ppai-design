package dsi.entrega3.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estado {
    /* Atributos de la clase Estado */
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
        return nombre.equalsIgnoreCase("Finalizada");
    }

    public Boolean EsIniciada()
    {
        if (nombre.equalsIgnoreCase("Iniciada"))
        {
            return true;
        }
        return false;
    }
}
