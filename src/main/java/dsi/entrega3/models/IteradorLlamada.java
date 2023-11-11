package dsi.entrega3.models;

import dsi.entrega3.models.interfaces.Iterador;

import java.util.List;

public class IteradorLlamada<Llamada> implements Iterador<Llamada> {

    private int posicionActual;
    private List<Object> llamadas;
    List<Object> filtros;

    public IteradorLlamada(List<Object> elementos) {
        this.llamadas = elementos;
    }

    @Override
    public void primero() {
        posicionActual = 0;
    }

    @Override
    public void siguiente() {

    }

    @Override
    public Llamada actual() {
        return null;
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
        return false;
    }
}
