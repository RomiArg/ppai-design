package dsi.entrega3.models;

import dsi.entrega3.models.interfaces.IteradorPregunta;

import java.util.ArrayList;
import java.util.List;

public class IteradorPreguntaImpl implements IteradorPregunta {

    private int posicionActual;
    private List<Pregunta> preguntas;
    private List<RespuestaPosible> filtros;

    // Metodo new, crea el iterador en base a la lista que recibe, como recibe una lista de objetos pregunta si es una
    // instancia de Pregunta y la va guardando en la lista de preguntas que debe recorrer
    public IteradorPreguntaImpl(List<Object> elementos)  {
        this.preguntas = new ArrayList<>();
        for (Object elemento : elementos) {
            if (elemento instanceof Pregunta) {
                this.preguntas.add((Pregunta) elemento);
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
    public Pregunta actual() {
        return preguntas.get(posicionActual);
    }

    @Override
    public boolean haTerminado() {
        return posicionActual >= preguntas.size();
    }

    @Override
    public boolean cumpleFiltro(List<RespuestaPosible> filtros) {
        for (Pregunta pregunta : preguntas)
        {
            if (!pregunta.esEncuestaDeCliente(filtros))
            {
                return false;
            }
        }
        return true;
    }

}
