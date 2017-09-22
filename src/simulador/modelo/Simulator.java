package simulador.modelo;

import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class Simulator extends Observable {

    // <editor-fold desc="Constructores" defaultstate="collapsed">
    public Simulator() {
        System.out.println("Inicializando modelo..");
        maquina = new Maquina();
    }

    // </editor-fold>
    // <editor-fold desc="Metodos" defaultstate="collapsed">
    public void abrir_archivo(String nombre) {
        try {
            maquina.set_maquina(Archivos.recuperar_xml(nombre));
        } catch (ParserConfigurationException | TransformerException ex) {
            Logger.getLogger(Simulator.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("guardando la maquina ventana..");
        //actualizar el observador
    }

    public void guardar_archivo(String nombre) {
        try {
            Archivos.guardar_xml(nombre,maquina);
        } catch (ParserConfigurationException | TransformerException ex) {
            Logger.getLogger(Simulator.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("guardando la maquina ventana..");
    }

    public void crea_estado(int tipo, String nom) {
        maquina.agregar_estado(tipo, nom);
        //actualizar la vista
    }

    public void limpiar() {
        maquina.limpiar();
        System.out.println("limpiando la maquina ventana..");
        //actualizar el observador
    }

    public void verificar_hilera(String hilera) {
        maquina.verificar(hilera);
        System.out.println("verificando hilera modelo..");
    }

    // </editor-fold>
    // <editor-fold desc="Atributos" defaultstate="collapsed">
    private final Maquina maquina;
    // </editor-fold>

}
