package dsi.entrega3.controllers;

import dsi.entrega3.models.Llamada;
import dsi.entrega3.services.GestorEncuesta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/ivr")
public class RestController {

    private GestorEncuesta gestorEncuesta;

    public RestController (GestorEncuesta gestor){
        this.gestorEncuesta = gestor;
    }

    @GetMapping("encuestas/{fechaInicio}/{fechaFin}")
    public ResponseEntity<List<Llamada>> getLlamadas(
            @PathVariable String fechaInicio,
            @PathVariable String fechaFin) {
        // Convertir las fechas a LocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime fechaInicioDateTime = LocalDateTime.parse(fechaInicio + " 00:00:00", formatter);
        LocalDateTime fechaFinDateTime = LocalDateTime.parse(fechaFin + " 23:59:59", formatter);
        return ResponseEntity.ok(gestorEncuesta.tomarSeleccionFechasFiltros(fechaInicioDateTime, fechaFinDateTime));
    }
}
