/*
Esteban Espinoza Fallas 402290345
José Fabio Alfaro Quesada 207580494
*/
package simulador.modelo;

import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "nodo")
@XmlType(propOrder = {"name", "tipo", "x", "y", "radio", "paths"})
public class Nodo {
    
    // <editor-fold desc="Constructores" defaultstate="collapsed">
    private Nodo() {
    }

    public Nodo(int tipo, String name) {
        this.name = name;
        this.paths = new ArrayList<>();
        this.tipo = tipo;
        this.x = 25;
        this.y = 25;
        this.radio = 20;
    }
    // </editor-fold>
    
    // <editor-fold desc="Metodos" defaultstate="collapsed">
    public void addPaths(Path p) {
        paths.add(p);
    }

    @XmlElement(name = "x")
    public int getx() {
        return x;
    }

    public void setx(int x) {
        this.x = x;
    }

    @XmlElement(name = "y")
    public int gety() {
        return y;
    }

    public void sety(int y) {
        this.y = y;
    }

    @XmlElementWrapper(name = "listPaths")
    @XmlElement(name = "path")
    public ArrayList<Path> getpaths() {
        return paths;
    }

    public void setpaths(ArrayList<Path> p) {
        this.paths = p;
    }

    @XmlAttribute(name = "nombre")
    public String getname() {
        return name;
    }

    public void setname(String n) {
        this.name = n;
    }

    @XmlElement(name = "tipo")
    public int gettipo() {
        return tipo;
    }

    public void settipo(int t) {
        this.tipo = t;
    }

    @XmlElement(name = "radio")
    public int getradio() {
        return radio;
    }

    public void setradio(int r) {
        this.radio = r;
    }
    
    // <editor-fold desc="Dibujar" defaultstate="collapsed">
    public void dibujar(Graphics2D g) {
        FontMetrics fm = g.getFontMetrics();
        if (tipo == 1) {
            g.setColor(new Color(0, 60, 255));
        } else {
            if (tipo == 2) {
                g.setColor(new Color(0, 255, 0));
            } else {
                g.setColor(new Color(255, 0, 0));
            }
        }
        Ellipse2D el = new Ellipse2D.Double(x - radio, y - radio, 2 * radio, 2 * radio);
        g.fill(el);
        g.setColor(Color.BLACK);
        Ellipse2D e2 = new Ellipse2D.Double(x - radio, y - radio, 2 * radio, 2 * radio);
        g.draw(e2);
        g.setColor(Color.BLACK);
        g.setFont(TIPO_BASE);
        g.drawString(
                name,
                x - fm.stringWidth(name) / 2 - 1,
                y + fm.getAscent() / 2);
    }
    // </editor-fold>
    
    // </editor-fold>
    
    // <editor-fold desc="Atributos" defaultstate="collapsed">
    private static final Font TIPO_BASE
            = new Font(Font.SANS_SERIF, Font.PLAIN, 18);

    private String name;
    private int tipo;
    private ArrayList<Path> paths;
    private int x;
    private int y;
    private int radio;
    // </editor-fold>
}
