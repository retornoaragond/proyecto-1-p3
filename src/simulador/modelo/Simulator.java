package simulador.modelo;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
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
        setChanged();
        notifyObservers();
    }

    public void guardar_archivo(String nombre) {
        try {
            Archivos.guardar_xml(nombre, maquina);
        } catch (ParserConfigurationException | TransformerException ex) {
            Logger.getLogger(Simulator.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("guardando la maquina ventana..");
    }

    public void crea_estado(int tipo, String nom) {
        maquina.agregar_estado(tipo, nom);
        System.out.println("creo estado..");
        //actualizar la vista
        setChanged();
        System.out.println("seteo el cambio..");
        notifyObservers();
        System.out.println("informo del cambio..");
    }

    public void limpiar() {
        maquina.limpiar();
        System.out.println("limpiando la maquina ventana..");
        //actualizar el observador
        setChanged();
        notifyObservers();
    }

    public void verificar_hilera(String hilera) {
        maquina.verificar(hilera);
        System.out.println("verificando hilera modelo..");
    }

    public void dibujar(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        for (Nodo n : maquina.get_maquina()) {
            n.dibujar(g);
        }
    }

    public void seleccionar(Point p) {
        seleccionada = find(p);
    }

    public int find(Point p) {
        int i = 0;
        for (Nodo d : maquina.get_maquina()) {

            Ellipse2D el = new Ellipse2D.Double(d.obtPos().x - d.getradio(),
                    d.obtPos().y - d.getradio(),
                    2 * d.getradio(), 2 * d.getradio()
            );
            if (el.contains(p)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public void arrastrar(Point p) {
        if (seleccionada != -1) {
            mover(p);
            System.out.println("Arrastrando" + p.toString());
        }
    }

    public void mover(Point p) {
        Nodo est = maquina.get_maquina().get(seleccionada);
        est.setobtPos(p);
        setChanged();
        notifyObservers();
    }

    // </editor-fold>
    // <editor-fold desc="Atributos" defaultstate="collapsed">
    private final Maquina maquina;
    private int seleccionada;
    // </editor-fold>

}
