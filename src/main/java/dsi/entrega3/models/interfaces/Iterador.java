package dsi.entrega3.models.interfaces;

import java.util.Iterator;
import java.util.List;

public interface Iterador {
    void primero();
    void siguiente();
    Object actual();
    boolean haTerminado();
    boolean cumpleFiltro(List<Object> filtros);
}
