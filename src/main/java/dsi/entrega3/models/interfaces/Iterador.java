package dsi.entrega3.models.interfaces;

import java.util.List;

// Interfaz de iterador con cada metodos declarados que se implementaran en los Iteradores concretos
public interface Iterador<T, W> {
    void primero();
    void siguiente();
    T actual();
    boolean haTerminado();
    boolean cumpleFiltro(List<W> filtros);
}
