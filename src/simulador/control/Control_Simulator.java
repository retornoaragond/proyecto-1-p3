package simulador.control;

import java.util.Observer;
import simulador.modelo.Simulator;

public class Control_Simulator {

    // <editor-fold desc="Constructores" defaultstate="collapsed">
    public Control_Simulator(Simulator datos) {
        System.out.println("Inicializando clase de control..");
        System.out.println("Asociando el modelo..");
        this.datos = datos;
    }
    // </editor-fold>

    // <editor-fold desc="Métodos" defaultstate="collapsed">
    public void registrar(Observer obs) {
        System.out.printf("Registrando observador: %s..%n", obs);
        datos.addObserver(obs);
    }

    public void eliminarregistro(Observer obs) {
        System.out.printf("Eliminado observador: %s..%n", obs);
        datos.deleteObserver(obs);
    }
    
    public void abrirarchivo(String archivo){
        datos.abrir_archivo(archivo);
    }
    
    public void guardararchivo(String archivo){
        datos.abrir_archivo(archivo);
    }
    
    public void limpiar(){
        datos.limpiar();
    }
    
    public void crea_est_Inicial(String var){
        
    }

    // </editor-fold>
    
    // <editor-fold desc="Métodos" defaultstate="collapsed">
    public void cerrarAplicacion() {
        System.out.println("Finalizando aplicación..");

        System.exit(0);
    }
    // </editor-fold>
    
    // <editor-fold desc="Atributos" defaultstate="collapsed">
    private final Simulator datos;
    // </editor-fold>
}
