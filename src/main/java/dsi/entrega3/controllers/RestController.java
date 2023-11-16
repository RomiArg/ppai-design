package dsi.entrega3.controllers;

import dsi.entrega3.models.Llamada;
import dsi.entrega3.services.GestorEncuesta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// Hace la comunicacion con la pantalla que es otro proyecto aparte programada en JavaScript
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/ivr")
@CrossOrigin(origins = "http://localhost:3000")
public class RestController {

    private GestorEncuesta gestorEncuesta;

    public RestController (GestorEncuesta gestor){
        this.gestorEncuesta = gestor;
    }

    // Recibe las fechas como String del front y las formatea en LocalDateTime y llama
    // al metodo tomar seleccion de fechas del gestor
    @GetMapping("encuestas/{fechaInicio}/{fechaFin}")
    public ResponseEntity<List<Llamada>> consultarEncuestas(
            @PathVariable String fechaInicio,
            @PathVariable String fechaFin) {
        // Convertir las fechas a LocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime fechaInicioDateTime = LocalDateTime.parse(fechaInicio + " 00:00:00", formatter);
        LocalDateTime fechaFinDateTime = LocalDateTime.parse(fechaFin + " 23:59:59", formatter);

        return ResponseEntity.ok(gestorEncuesta.tomarSeleccionFechasFiltros(fechaInicioDateTime, fechaFinDateTime));
    }

    @GetMapping("encuestas/{id}")
    public ResponseEntity<List<String>> getEncuesta(
            @PathVariable Long id) {
        gestorEncuesta.tomarSeleccionLlamada(id);

        // Obtener la descripción de la encuesta
        String descripcionEncuesta = gestorEncuesta.getDescripcionEncuesta();

        // Obtener las preguntas y respuestas
        List<String> preguntasYRespuestas = gestorEncuesta.getPreguntasYRespuestas();

        // Combinar la descripción y las preguntas/respuestas en una lista
        List<String> response = new ArrayList<>();
        response.add(descripcionEncuesta);
        response.addAll(preguntasYRespuestas);

        // Devolver la respuesta ResponseEntity
        return ResponseEntity.ok(response);
    }

    @GetMapping("encuestas/CSV")
    public ResponseEntity generarCSV(){
        return ResponseEntity.ok("OK");
    };
}
