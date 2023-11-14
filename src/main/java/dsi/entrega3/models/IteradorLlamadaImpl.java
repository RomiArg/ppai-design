package dsi.entrega3.models;

import dsi.entrega3.models.interfaces.IteradorLlamada;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class IteradorLlamadaImpl implements IteradorLlamada {

    private int posicionActual;
    private List<Llamada> llamadas;
    List<Object> filtros;


    // Metodo new, crea el iterador en base a la lista que recibe, como recibe una lista de objetos pregunta si es una
    // instancia de Llamada y la va guardando en la lista de llamadas que debe recorrer
    public IteradorLlamadaImpl(List<Object> elementos, List<Object> filtros) {
        this.llamadas = new ArrayList<>();
        for (Object elemento : elementos) {
            if (elemento instanceof Llamada) {
                this.llamadas.add((Llamada) elemento);
            }
        }
        this.filtros = filtros;
    }

    // Setea la posicion actual como cero para que comience el recorrido
    @Override
    public void primero() {
        posicionActual = 0;
    }

    // Suma uno a la posicion actual
    @Override
    public void siguiente() {
        posicionActual ++;
    }

    // Devuelve una Llamada si esta cumple los filtros necesarios
    @Override
    public Llamada actual() {

        this.filtros.add(llamadas.get(posicionActual));

        if (cumpleFiltro(filtros)){
            this.filtros.remove(llamadas.get(posicionActual));
            return llamadas.get(posicionActual);}

        this.filtros.remove(llamadas.get(posicionActual));
        return null;
    }

    // Devuelve un false si la posicion actual es menor al tamaño de la lista para que siga el recorrido
    @Override
    public boolean haTerminado() {
        return posicionActual >= llamadas.size();
    }

    // Busca una llamada con en un periodo especifico y si tiene encuesta
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

