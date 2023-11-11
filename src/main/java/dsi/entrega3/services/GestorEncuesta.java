package dsi.entrega3.services;

import dsi.entrega3.models.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class GestorEncuesta {

    // Atributos de la clase GestorEncuesta

    private LocalDateTime fechaInicioPeriodo;
    private LocalDateTime fechaFinPeriodo;
    private String nombreCliente;
    private float duracionLlamada;
    private ArrayList<RespuestaDeCliente> rtasCliente;
    private ArrayList<RespuestaPosible> rtasSeleccionadas;
    private ArrayList<Pregunta> descripcionPreguntas;
    private Llamada llamadaSeleccionada;
    private ArrayList<Encuesta> encuesta;
    private ArrayList<Llamada> llamadas;
    private String nombreLlamada;
    private String estadoActual;
    private ArrayList<String> preguntasYRespuestas;
    private String descripcionEncuesta;

    public GestorEncuesta()
    {
        this.llamadas =             new ArrayList<Llamada>();
        this.encuesta =             new ArrayList<Encuesta>();
        this.rtasCliente =          new ArrayList<RespuestaDeCliente>();
        this.rtasSeleccionadas =    new ArrayList<RespuestaPosible>();
        this.descripcionPreguntas = new ArrayList<Pregunta>();
    }


    //Este método guarda las fechas que entran por parámetros como variables del gestor y a su vez llama al método
    //BuscarLlamadasConEncuestaRespondida y guarda el resultado de este en una variable local.
    public void TomarSeleccionFechasFiltros(LocalDateTime fechaIniP, LocalDateTime fechaFinP)
    {
        fechaInicioPeriodo = fechaIniP;
        fechaFinPeriodo = fechaFinP;

        ArrayList<Llamada> llamadasRespondidas = buscarLlamadasConEncuestaRespondida();
        if (llamadasRespondidas.size() == 0)
        {
            String mensaje = "No existen llamadas con encuestas respondidas en el período indicado."
                    + "\nPor favor ingresar una nueva fecha de inicio y fin de periodo.";
            //return null;
        }
        else
        {
            //PantallaEncuesta.pedirSeleccionLlamada(llamadasRespondidas);
        }
    }
    // Este método filtra las llamadas por su periodo y si tiene encuesta respondida y devuelve la Lista de llamadas.
    public ArrayList<Llamada> buscarLlamadasConEncuestaRespondida()
    {
        ArrayList<Llamada> llamadasFiltradas = new ArrayList<Llamada>();
        for (Llamada llamada : this.llamadas)
        {
            if (llamada.esDePeriodo(fechaInicioPeriodo, fechaFinPeriodo) && llamada.tieneEncuestaRespondida())
            {
                llamadasFiltradas.add(llamada);
            }
        }
        return llamadasFiltradas;
    }
    /*
    // Métodos constructores de la clase
    public GestorEncuesta(PantallaEncuesta pantallaEncuesta)
    {
        this.PantallaEncuesta = pantallaEncuesta;
        Llamadas = new List<Llamada>();
        Encuesta = new List<Encuesta>();
        RtasCliente = new List<RespuestaDeCliente>();
        RtasSeleccionadas = new List<RespuestaPosible>();
        DescripcionPreguntas = new List<Pregunta>();
    }

    public GestorEncuesta(DateTime fechaInicioPeriodo, DateTime fechaFinPeriodo, string nombreCliente, int duracionLlamada, List<RespuestaDeCliente> rtasCliente, List<RespuestaPosible> rtasSeleccionadas, List<Pregunta> descripcionPreguntas, Llamada llamadaSeleccionada, PantallaEncuesta pantallaEncuesta, string nombreLlamada, string estadoActual, List<string> preguntasYRespuestas, string descripcionEncuesta)
    {
        this.fechaInicioPeriodo = fechaInicioPeriodo;
        this.fechaFinPeriodo = fechaFinPeriodo;
        this.nombreCliente = nombreCliente;
        this.duracionLlamada = duracionLlamada;
        this.rtasCliente = rtasCliente;
        this.rtasSeleccionadas = rtasSeleccionadas;
        this.descripcionPreguntas = descripcionPreguntas;
        this.llamadaSeleccionada = llamadaSeleccionada;
        this.encuesta = new List<Encuesta>();
        this.pantallaEncuesta = new PantallaEncuesta(this);
        this.llamadas = new List<Llamada>();
        this.nombreLlamada = nombreLlamada;
        this.estadoActual = estadoActual;
        this.preguntasYRespuestas = preguntasYRespuestas;
        this.descripcionEncuesta = descripcionEncuesta;
    }

    // Métodos que son utilizados en la implementación del CU

    public void ConsultarEncuesta()
    {
        PantallaEncuesta.pedirFechasFiltroPeriodo();

    }




    //Setea la variable LlamadaElegida del gestor y realiza la búsqueda de los datos de la Llamada y la Encuesta
    //y manda un mensaje a la Pantalla para que muestre los datos en la tabla
    public void TomarSeleccionLlamada(Llamada llamadaElegida)
    {
        LlamadaSeleccionada = llamadaElegida;
        BuscarDatosLlamada();
        BuscarRespuestas();
        Encuesta Enc = BuscarPreguntasDeEncuesta(RtasSeleccionadas);
        DescripcionEncuesta = Enc.Descripcion;
        PreguntasYRespuestas = BuscarDescripcionEncuestaYPreguntas(Enc);
        PantallaEncuesta.pedirOpcionVisualizacion();
    }

    // Obtiene los datos de la Llamada guardada en el gestor y llama los métodos en la clase Llamada que necesita
    public void BuscarDatosLlamada()
    {
        List<string> NombreYEstado = llamadaSeleccionada.GetNombreClienteYEstado();
        NombreCliente = NombreYEstado[0];
        EstadoActual = NombreYEstado[1];
        DuracionLlamada = llamadaSeleccionada.Duracion;
    }*/

    // Busca las respuestas posibles a través de las respuestas del Cliente de la Llamada y
    //las guarda en la variable del Gestor
    public void buscarRespuestas()
    {
        rtasCliente = llamadaSeleccionada.getRespuestasDeEncuesta();
        rtasSeleccionadas = new ArrayList<RespuestaPosible>();

        for (RespuestaDeCliente res : rtasCliente)
        {
            rtasSeleccionadas.add(res.getRespuestaSeleccionada());
        }
    }

    // Busca de la clase Encuesta las preguntas que la componen y compara con las respuesta
   // guardadas anteriormente si son iguales y si lo son devuelve la encuesta

    public Encuesta buscarPreguntasDeEncuesta(ArrayList<RespuestaPosible> respuestas)
    {
        for (Encuesta encuesta : encuesta)
        {
            if (encuesta.esEncuestaDeCliente(respuestas))
            {
                return encuesta;
            }
        }
        return null;
    }

    // Busca en la Encuesta las preguntas y las respustas guardadas y las ordena en una lista de strings comparandolas
    public ArrayList<String> buscarDescripcionEncuestaYPreguntas(Encuesta enc)
    {
        ArrayList<String> encuestaArmada = new ArrayList<String>();
        for (Pregunta preg : enc.getPreguntas())
        {
            for (RespuestaPosible res : rtasSeleccionadas)
            {
                if (preg.getRespuestaPosibles().contains(res))
                {
                    encuestaArmada.add(preg.getPregunta());
                    encuestaArmada.add(res.getDescripcion());
                }
            }
        }
        return encuestaArmada;
    }
}
