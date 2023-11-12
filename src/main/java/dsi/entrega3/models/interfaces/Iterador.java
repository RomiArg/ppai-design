package dsi.entrega3.models.interfaces;

import java.util.List;

public interface Iterador<T, W> {
    void primero();
    void siguiente();
    T actual();
    boolean haTerminado();
    boolean cumpleFiltro(List<W> filtros);
}
