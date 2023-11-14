package dsi.entrega3.services;

import dsi.entrega3.models.*;
import dsi.entrega3.models.interfaces.*;
import dsi.entrega3.repositories.EncuestaRepository;
import dsi.entrega3.repositories.LlamadaRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Getter
@AllArgsConstructor
public class GestorEncuesta implements IAgregado {

    // Atributos de la clase GestorEncuesta
    private LocalDateTime fechaInicioPeriodo;
    private LocalDateTime fechaFinPeriodo;
    private String nombreCliente;
    private float duracionLlamada;
    private List<RespuestaDeCliente> rtasCliente;
    private List<RespuestaPosible> rtasSeleccionadas;
    private List<Pregunta> descripcionPreguntas;
    private Llamada llamadaSeleccionada;
    private List<Encuesta> encuestas;
    private List<Llamada> llamadas;
    private String nombreLlamada;
    private String estadoActual;
    private List<String> preguntasYRespuestas;
    private String descripcionEncuesta;
    private final LlamadaRepository llamadaRepository;
    private final EncuestaRepository encuestaRepository;
    private List<Object> filtrosLlamadas;

    @Autowired
    public GestorEncuesta(LlamadaRepository llamadaRepository, EncuestaRepository encuestaRepository) {
        this.llamadaRepository = llamadaRepository;
        this.encuestaRepository = encuestaRepository;
        this.llamadas = llamadaRepository.findAll();
        this.encuestas = encuestaRepository.findAll();
    }

    //Este método guarda las fechas que entran por parámetros como variables del gestor y a su vez llama al método
    //BuscarLlamadasConEncuestaRespondida y guarda el resultado de este en una variable local.
    public List<Llamada> tomarSeleccionFechasFiltros(LocalDateTime fechaIniP, LocalDateTime fechaFinP)
    {
        this.fechaInicioPeriodo = fechaIniP;
        this.fechaFinPeriodo = fechaFinP;

        List<Llamada> llamadasRespondidas = buscarLlamadasConEncuestaRespondida();
        return llamadasRespondidas;
    }

    // Este método filtra las llamadas por su periodo y si tiene encuesta respondida y devuelve la Lista de llamadas.
    public List<Llamada> buscarLlamadasConEncuestaRespondida()
    {
        List<Llamada> llamadasFiltradas = new ArrayList<>();

        this.filtrosLlamadas = new ArrayList<>();
        this.filtrosLlamadas.add(fechaInicioPeriodo);
        this.filtrosLlamadas.add(fechaFinPeriodo);

        IteradorLlamadaImpl iterador = (IteradorLlamadaImpl) crearIterador(new ArrayList<>(this.llamadas));
        iterador.primero();
        while (!iterador.haTerminado())
        {
            Llamada llamada = iterador.actual();
            if (llamada != null)
            {
                llamadasFiltradas.add(llamada);
            }

            iterador.siguiente();
        }
        return llamadasFiltradas;
    }

    // Métodos que son utilizados en la implementación del CU
    public void consultarEncuesta(){
    };

    //Setea la variable LlamadaElegida del gestor y realiza la búsqueda de los datos de la Llamada y la Encuesta
    //y manda un mensaje a la Pantalla para que muestre los datos en la tabla
    public void tomarSeleccionLlamada(Long idLlamadaElegida)
    {
        Llamada llamadaElegida = llamadaRepository.findById(idLlamadaElegida).orElseThrow();
        this.llamadaSeleccionada = llamadaElegida;
        buscarDatosLlamada();
        buscarRespuestas();
        System.out.println(llamadaSeleccionada.getId());
        Encuesta encuesta = buscarPreguntasDeEncuesta(rtasSeleccionadas);
        this.descripcionEncuesta = encuesta.getDescripcion();
        this.preguntasYRespuestas = buscarDescripcionEncuestaYPreguntas(encuesta);
        System.out.println(buscarDescripcionEncuestaYPreguntas(encuesta));
    }

    // Obtiene los datos de la Llamada guardada en el gestor y llama los métodos en la clase Llamada que necesita
    public void buscarDatosLlamada()
    {
        List<String> nombreYEstado = llamadaSeleccionada.getNombreClienteYEstado();
        this.nombreCliente = nombreYEstado.get(0);
        this.estadoActual = nombreYEstado.get(1);
        this.duracionLlamada = llamadaSeleccionada.getDuracion();
    }

    // Busca las respuestas posibles a través de las respuestas del Cliente de la Llamada y
    //las guarda en la variable del Gestor
    public void buscarRespuestas()
    {
        this.rtasCliente = llamadaSeleccionada.getRespuestasDeEncuesta();
        this.rtasSeleccionadas = new ArrayList<RespuestaPosible>();
        for (RespuestaDeCliente res : rtasCliente)
        {
            this.rtasSeleccionadas.add(res.getRespuestaSeleccionada());
        }
    }

    // Busca de la clase Encuesta las preguntas que la componen y compara con las respuesta
   // guardadas anteriormente si son iguales y si lo son devuelve la encuesta

    public Encuesta buscarPreguntasDeEncuesta(List<RespuestaPosible> respuestas)
    {
        IteradorEncuestaImpl iterador = (IteradorEncuestaImpl) crearIterador(new ArrayList<>(encuestas));

        iterador.primero();
        while (!iterador.haTerminado()){
            Encuesta encuesta = iterador.actual();
            if(encuesta.esEncuestaDeCliente(respuestas)){
                return encuesta;
            }
            iterador.siguiente();
        }
        return null;
    }

    // Busca en la Encuesta las preguntas y las respuestas guardadas y las ordena en una lista de strings comparandolas
    public List<String> buscarDescripcionEncuestaYPreguntas(Encuesta enc) {
        List<String> encuestaArmada = new ArrayList<>();

        for (Pregunta preg : enc.getPreguntas()) {
            for (RespuestaPosible res : rtasSeleccionadas) {
                //PROBLEMAA
                if (preg.getRespuestaPosibles().stream().anyMatch(resp -> resp.getId().equals(res.getId()))) {
                    encuestaArmada.add(preg.getPregunta());
                    System.out.println("AAAAAAA"+preg.getPregunta());
                    encuestaArmada.add(res.getDescripcion());
                    System.out.println("AAAAAAA"+res.getDescripcion());
                }
            }
        }
        return encuestaArmada;
    }


    @Override
    public Iterador crearIterador(List<Object> elementos) {
        if (!elementos.isEmpty()) {
            Class<?> tipoElemento = elementos.get(0).getClass();

            if (tipoElemento.equals(Llamada.class)) {
                return new IteradorLlamadaImpl(elementos, this.filtrosLlamadas);
            } else if (tipoElemento.equals(Encuesta.class)) {
                return new IteradorEncuestaImpl(elementos);
            }
        }
        throw new IllegalArgumentException("Tipo de elemento no compatible.");
    }
}
