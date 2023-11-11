package dsi.entrega3.models;

import dsi.entrega3.models.interfaces.Iterador;

import java.util.List;

public class IteradorPregunta implements Iterador<Pregunta> {

    private int posicionActual;
    private List<Pregunta> preguntas;


    public IteradorPregunta(List<Pregunta> elementos) {
        this.preguntas = elementos;
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
    public Pregunta actual() {
        return null;
    }

    @Override
    public boolean haTerminado() {
        if (preguntas.get(posicionActual) != null){
            return true;
        }
        else {return false; }
    }

    @Override
    public boolean cumpleFiltro(List<Object> filtros) {
        return false;
    }
}
