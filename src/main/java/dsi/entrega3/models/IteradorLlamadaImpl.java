package dsi.entrega3.models;

import dsi.entrega3.models.interfaces.IteradorLlamada;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class IteradorLlamadaImpl implements IteradorLlamada {

    private int posicionActual;
    private List<Llamada> llamadas;
    List<Object> filtros;

    public IteradorLlamadaImpl(List<Object> elementos, List<Object> filtros) {
        this.llamadas = new ArrayList<>();
        for (Object elemento : elementos) {
            if (elemento instanceof Llamada) {
                this.llamadas.add((Llamada) elemento);
            }
        }
        this.filtros = filtros;
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
    public Llamada actual() {
        this.filtros.add(llamadas.get(posicionActual));
        if (cumpleFiltro(filtros)){
            this.filtros.remove(llamadas.get(posicionActual));
            return llamadas.get(posicionActual);}
        this.filtros.remove(llamadas.get(posicionActual));
        return null;
    }

    @Override
    public boolean haTerminado() {
        return posicionActual >= llamadas.size();
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

