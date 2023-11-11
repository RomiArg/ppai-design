package dsi.entrega3.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespuestaDeCliente {
        // Atributos de la clase RespuestaDeCliente
        private LocalDateTime fechaDeEncuesta;
        private RespuestaPosible respuestaSeleccionada;

        // Este método convierte a los atributos en string para mostrarlos
        public String mostrarDatos()
        {
            StringBuilder sb = new StringBuilder();

            sb.append("Fecha De Encuesta: ").append(fechaDeEncuesta.toString());
            sb.append("Respuesta Seleccionada: ").append(respuestaSeleccionada.mostrarDatos());

            return sb.toString();
        }

         //Método que se utiliza en la implementación del CU
        public String getDescripcionRta()
        {
            return respuestaSeleccionada.getDescripcionRta();
        }
}
