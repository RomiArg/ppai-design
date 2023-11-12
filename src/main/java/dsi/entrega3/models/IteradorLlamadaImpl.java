package dsi.entrega3.models;

import dsi.entrega3.models.interfaces.IteradorLlamada;

import java.util.ArrayList;
import java.util.List;

public class IteradorLlamadaImpl implements IteradorLlamada {

    private int posicionActual;
    private List<Llamada> llamadas;
    List<String> filtros;

    public IteradorLlamadaImpl(List<Object> elementos) {
        //this.llamadas = elementos;
        this.llamadas = new ArrayList<>();
        for (Object elemento : elementos) {
            if (elemento instanceof Llamada) {
                this.llamadas.add((Llamada) elemento);
            } else {
                // Manejar el caso en que el elemento no es de tipo Llamada, si es necesario

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
    public dsi.entrega3.models.Llamada actual() {
        return llamadas.get(posicionActual);
    }

    @Override
    public boolean haTerminado() {
        if (llamadas.get(posicionActual) != null){
            return true;
        }
        else {return false; }
    }

    @Override
    public boolean cumpleFiltro(List<String> filtros) {
        this.filtros = filtros;
        return false;
    }
}
