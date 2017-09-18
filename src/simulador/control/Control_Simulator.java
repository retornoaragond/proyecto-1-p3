package simulador.control;

import java.util.Observer;
import java.util.Properties;
import simulador.modelo.Simulator;

public class Control_Simulator {

    // <editor-fold desc="Constructores" defaultstate="collapsed">
    public Control_Simulator(Simulator datos) {
        System.out.println("Inicializando clase de control..");
        System.out.println("Asociando el modelo..");
        this.datos = datos;
    }
    // </editor-fold>
    
    // <editor-fold desc="Métodos generales (control)" defaultstate="collapsed">
    public void registrar(Observer obs) {
        System.out.printf("Registrando observador: %s..%n", obs);
        datos.addObserver(obs);
    }

    public void eliminarregistro(Observer obs) {
        System.out.printf("Eliminado observador: %s..%n", obs);
        datos.deleteObserver(obs);
    }

    public String obtenerPropiedad(String id) {
        return propiedades.getProperty(id);
    }
    // </editor-fold>
    // <editor-fold desc="Métodos" defaultstate="collapsed">
    public void cerrarAplicacion() {
        System.out.println("Finalizando aplicación..");

        System.exit(0);
    }
    // </editor-fold>
    // <editor-fold desc="Atributos" defaultstate="collapsed">
    private Properties propiedades;
    private final Simulator datos;
    // </editor-fold>
}
