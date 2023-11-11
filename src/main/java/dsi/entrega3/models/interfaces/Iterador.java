package dsi.entrega3.models.interfaces;

import java.util.List;

public interface Iterador<T> {
    void primero();
    void siguiente();
    T actual();
    boolean haTerminado();
    boolean cumpleFiltro(List<Object> filtros);
}
