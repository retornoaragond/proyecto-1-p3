package simulador.modelo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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
        System.out.printf("hilera %s", hilera);
        if (maquina.verificar(hilera)) {
            String m = "Hilera Aceptada: " + hilera;
            JOptionPane.showMessageDialog(null, m);
        } else {
            String m = "Hilera No Aceptada: " + hilera;
            JOptionPane.showMessageDialog(null, m);
        }
        System.out.println("verificando hilera modelo..");
    }

    public void dibujar(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        if (origen != -1) {
            lineaprevia(g);
        }
        for (Nodo n : maquina.get_maquina()) {

            for (Path a : n.getPathList()) {
                a.dibujar(g, n.obtPos(), (ArrayList<Nodo>) maquina.get_maquina());
            }
            g.setStroke(new BasicStroke(2.0f));
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

    public void lineaprevia(Graphics2D g) {
        g.setColor(Color.GRAY);
        float grosor = 2;
        float[] style = {10, 5};
        g.setColor(Color.RED);
        g.setStroke(new BasicStroke(grosor, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER, 10.0f, style, 0));
        g.drawLine(
                maquina.get_maquina().get(origen).obtPos().x,
                maquina.get_maquina().get(origen).obtPos().y,
                predest.x, predest.y);
    }

    public void predest(Point p) {
        predest = p;
        setChanged();
        notifyObservers();
    }

    public void agregarArista() {
        if (destino != -1) {
            String tag = JOptionPane.showInputDialog(
                    "tag:");
            maquina.get_maquina().get(origen).addPaths(
                    new Path(tag, destino));
        }
        setChanged();
        notifyObservers();
    }

    public void selorigen(Point p) {
        origen = find(p);
    }

    public void seldest(Point p) {
        destino = find(p);
    }

    public void desdest() {
        destino = -1;
    }

    public void deselorigen() {
        origen = -1;
    }

    public void deseleccionar() {
        seleccionada = -1;
    }

    // </editor-fold>
    // <editor-fold desc="Atributos" defaultstate="collapsed">
    private final Maquina maquina;
    private int seleccionada = -1;
    private int origen = -1;
    private int destino = -1;
    Point predest;
    // </editor-fold>

}
