package simulador.modelo;

import java.awt.Point;
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
@XmlType(propOrder = {"name", "tipo", "pos", "radio", "paths"})
public class Nodo {
private Nodo() {
    }
    public Nodo(int tipo, String name) {
        this.name = name;
        this.paths = new ArrayList<>();
        this.tipo = tipo;
        this.pos = new Point(25, 25);
        this.radio = 20;
    }

    

    public void addPaths(Path p) {
        paths.add(p);
    }

    @XmlElement(name = "Pos")
    public Point getPos() {
        return pos;
    }

    public void setPos(Point p) {
        pos = p;
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
        Ellipse2D el = new Ellipse2D.Double(pos.x - radio, pos.y - radio, 2 * radio, 2 * radio);
        g.fill(el);
        g.setColor(Color.BLACK);
        Ellipse2D e2 = new Ellipse2D.Double(pos.x - radio, pos.y - radio, 2 * radio, 2 * radio);
        g.draw(e2);
        g.setColor(Color.BLACK);
        g.setFont(TIPO_BASE);
        g.drawString(
                name,
                pos.x - fm.stringWidth(name) / 2 - 1,
                pos.y + fm.getAscent() / 2);
    }

    private static final Font TIPO_BASE
            = new Font(Font.SANS_SERIF, Font.PLAIN, 18);

    private String name;
    private int tipo;
    private ArrayList<Path> paths;
    private Point pos;
    private int radio;
}
