package dsi.entrega3.models;

import dsi.entrega3.models.interfaces.IteradorPregunta;

import java.util.ArrayList;
import java.util.List;

public class IteradorPreguntaImpl implements IteradorPregunta {

    private int posicionActual;
    private List<Pregunta> preguntas;
    private List<RespuestaPosible> filtros;


    public IteradorPreguntaImpl(List<Object> elementos) {
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
    public dsi.entrega3.models.Pregunta actual() {
        return preguntas.get(posicionActual);
    }

    @Override
    public boolean haTerminado() {
        if (preguntas.get(posicionActual) != null){
            return true;
        }
        else {return false; }
    }

    @Override
    public boolean cumpleFiltro(List<dsi.entrega3.models.RespuestaPosible> filtros) {
        this.filtros = filtros;
        for (dsi.entrega3.models.Pregunta pregunta : preguntas)
        {
            if (!pregunta.esEncuestaDeCliente(filtros))
            {
                return false;
            }
        }
        return false;
    }
}
