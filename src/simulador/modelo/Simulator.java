
package simulador.modelo;

import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class Simulator extends Observable{
    
    // <editor-fold desc="Constructores" defaultstate="collapsed">
    public Simulator() {
        System.out.println("Inicializando modelo..");
        aut = new Maquina();
        maquina = null;
    }
    
    // </editor-fold>
    
    // <editor-fold desc="Metodos" defaultstate="collapsed">
    public void abrir_archivo(String nombre){
        try {
           maquina = Archivos.recuperar_xml(nombre);
        } catch (ParserConfigurationException | TransformerException ex) {
            Logger.getLogger(Simulator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //actualizar el observador
    }
    
    public void guardar_archivo(String nombre){
        try {
           Archivos.guardar_xml(nombre);
        } catch (ParserConfigurationException | TransformerException ex) {
            Logger.getLogger(Simulator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    boolean isAccept(String s){
        if(aut.isND()){
            return aut.isAcceptND(s);
        }else{
            return aut.isAcceptD(s);
        }
    }
    
    public void limpiar(){
        //eliminar la lista que contiene los nodos de la maquina
        //actualizar el observador
    }
    
    // </editor-fold>
    
    // <editor-fold desc="Atributos" defaultstate="collapsed">
    private List<Nodo> maquina;
    private final Maquina aut;
    // </editor-fold>
}