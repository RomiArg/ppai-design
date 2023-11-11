package dsi.entrega3.models;

import dsi.entrega3.models.interfaces.Iterador;

import java.util.List;

public class IteradorEncuesta<Encuesta> implements Iterador<Encuesta> {

    private int posicionActual;
    private List<Object> encuestas;

    public IteradorEncuesta(List<Object> elementos) {
        this.encuestas = elementos;
    }

    @Override
    public void primero() {
        posicionActual = 0;
    }

    @Override
    public void siguiente() {

    }

    @Override
    public Encuesta actual() {
        return null;
    }

    @Override
    public boolean haTerminado() {
        if (encuestas.get(posicionActual) != null){
            return true;
        }
        else {return false; }
    }

    @Override
    public boolean cumpleFiltro(List<Object> filtros) {
        return false;
    }
}
