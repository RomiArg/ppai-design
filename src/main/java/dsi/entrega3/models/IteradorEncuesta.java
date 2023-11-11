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
