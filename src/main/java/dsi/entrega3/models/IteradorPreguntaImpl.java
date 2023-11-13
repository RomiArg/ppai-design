package dsi.entrega3.models;

import dsi.entrega3.models.interfaces.IteradorPregunta;

import java.util.ArrayList;
import java.util.List;

public class IteradorPreguntaImpl implements IteradorPregunta {

    private int posicionActual;
    private List<Pregunta> preguntas;
    private List<RespuestaPosible> filtros;


    public IteradorPreguntaImpl(List<Object> elementos, List<RespuestaPosible> filtros)  {
        this.preguntas = new ArrayList<>();
        for (Object elemento : elementos) {
            if (elemento instanceof Pregunta) {
                this.preguntas.add((Pregunta) elemento);
            }
        }
        this.filtros = filtros;
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
        if (cumpleFiltro(filtros))
            return preguntas.get(posicionActual);
        return preguntas.get(posicionActual+1);
    }

    @Override
    public boolean haTerminado() {
        return posicionActual >= preguntas.size();
    }

    @Override
    public boolean cumpleFiltro(List<RespuestaPosible> filtros) {
        for (Pregunta pregunta : preguntas)
        {
            System.out.println(pregunta.getPregunta());
            if (!pregunta.esEncuestaDeCliente(filtros))
            {
                return false;
            }
        }
        return true;
    }
}
