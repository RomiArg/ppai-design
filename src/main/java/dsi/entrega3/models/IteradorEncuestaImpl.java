package dsi.entrega3.models;

import dsi.entrega3.models.interfaces.IteradorEncuesta;

import java.util.ArrayList;
import java.util.List;

public class IteradorEncuestaImpl implements IteradorEncuesta {

    private int posicionActual;
    private List<Encuesta> encuestas;


    // Metodo new, crea el iterador en base a la lista que recibe, como recibe una lista de objetos pregunta si es una
    // instancia de Encuesta y la va guardando en la lista de encuestas que debe recorrer
    public IteradorEncuestaImpl(List<Object> elementos) {
        this.encuestas = new ArrayList<>();
        for (Object elemento : elementos) {
            if (elemento instanceof Encuesta) {
                this.encuestas.add((Encuesta) elemento);
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
    public Encuesta actual() {
        return encuestas.get(posicionActual);
    }

    @Override
    public boolean haTerminado() {
        return posicionActual >= encuestas.size();
    }

    @Override
    public boolean cumpleFiltro(List<Object> filtros) {
        return false;
    }
}
