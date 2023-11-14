package dsi.entrega3.models.interfaces;

import java.util.List;

// Tiene el metodo crear Iterador que sera implementado en la encuesta y gestor
public interface IAgregado{
      Iterador crearIterador(List<Object> elementos);
}
