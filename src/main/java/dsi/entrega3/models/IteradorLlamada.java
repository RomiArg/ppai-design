package dsi.entrega3.models;

import dsi.entrega3.models.interfaces.Iterador;

import java.util.List;

public class IteradorLlamada implements Iterador {

    private int actual;
    private List<Object> elementos;
    List<Object> filtros;

    public IteradorLlamada(List<Object> elementos, List<Object> filtros) {

        this.elementos = elementos;
        this.filtros = filtros;
    }

    @Override
    public void primero() {

    }

    @Override
    public void siguiente() {

    }

    @Override
    public Object actual() {
        return null;
    }

    @Override
    public boolean haTerminado() {
        return false;
    }

    @Override
    public boolean cumpleFiltro(List<Object> filtros) {
        return false;
    }
}
