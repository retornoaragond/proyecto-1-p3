package simulador.control;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import simulador.modelo.Simulator;

public class Control_Simulator {

    // <editor-fold desc="Constructores" defaultstate="collapsed">
    public Control_Simulator(Simulator datos) {
        System.out.println("Inicializando clase de control..");
        System.out.println("Asociando el modelo..");
        this.datos = datos;
    }

    public Control_Simulator() {
        this(new Simulator());
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
        try {
            datos.abrir_archivo(archivo);
            System.out.println("guardando la maquina ventana..");
        } catch (JAXBException ex) {
            Logger.getLogger(Control_Simulator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardararchivo(String archivo){
        datos.guardar_archivo(archivo);
        System.out.println("guardando la maquina ventana..");
    }

    public void limpiar() {
        datos.limpiar();
        System.out.println("limpiando la maquina ventana..");
    }

    public void cerrarAplicacion() {
        System.out.println("Finalizando aplicación..");
        System.exit(0);
    }

    public void crea_estado(int tipo, String nom) {
        datos.crea_estado(tipo, nom);
        System.out.println("Creando un estado nuevo Control..");
    }

    public void verificar_hilera(String hilera) {
        datos.verificar_hilera(hilera);
        System.out.println("verificando hilera Control..");
    }

    public void seleccionar(Point p) {
        datos.seleccionar(p);
    }

    public void arrastrar(Point p) {
        datos.arrastrar(p);
    }
    
    public void desseleccionar() {
        datos.deseleccionar();
    }
    
    public void selorigen(Point p) {
        datos.selorigen(p);
    }

    public void desselorigen() {
        datos.deselorigen();
    }

    public void seldest(Point p) {
        datos.seldest(p);
    }

    public void desdest() {
        datos.desdest();
    }

    public void predest(Point p) {
        datos.predest(p);
    }
    
    public void agregarArista() {
        datos.agregarArista();
    }
    
    // </editor-fold>

    // <editor-fold desc="Atributos" defaultstate="collapsed">
    private final Simulator datos;
    // </editor-fold>
}
