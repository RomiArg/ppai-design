package dsi.entrega3.models;

import dsi.entrega3.models.interfaces.Iterador;

import java.util.List;

public class IteradorEncuesta implements Iterador {

    private int actual;
    private List<Object> elementos;

    public IteradorEncuesta(List<Object> elementos) {
        this.elementos = elementos;
    }

    @Override
    public void primero() {
        elementos.get(0);
        actual += 1;
    }

    @Override
    public void siguiente() {
        elementos.get(actual);
        actual += 1;
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
