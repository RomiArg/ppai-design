package dsi.entrega3.models;

import dsi.entrega3.models.interfaces.IteradorLlamada;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class IteradorLlamadaImpl implements IteradorLlamada {

    private int posicionActual;
    private List<Llamada> llamadas;
    List<Object> filtros;

    public IteradorLlamadaImpl(List<Object> elementos) {
        this.llamadas = new ArrayList<>();
        for (Object elemento : elementos) {
            if (elemento instanceof Llamada) {
                this.llamadas.add((Llamada) elemento);
            }
        }
    }

    @Override
    public void primero() {
        posicionActual = 0;
    }

    @Override
    public void siguiente() {
        posicionActual ++;
    }

    @Override
    public dsi.entrega3.models.Llamada actual() {
        return llamadas.get(posicionActual);
    }

    @Override
    public boolean haTerminado() {
        if (llamadas.get(posicionActual) != null){
            return true;
        }
        else {return false; }
    }

    @Override
    public boolean cumpleFiltro(List<Object> filtros) {
        if (filtros.size() < 3) {
            return false;
        }

        LocalDateTime fechaInicioPeriodo = (LocalDateTime) filtros.get(0);
        LocalDateTime fechaFinPeriodo = (LocalDateTime) filtros.get(1);
        Llamada llamada = (Llamada) filtros.get(2);

        return llamada.esDePeriodo(fechaInicioPeriodo, fechaFinPeriodo) && llamada.tieneEncuestaRespondida();
    }
}
