package simulador.modelo;

import java.awt.Point;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Nodo {

    public Nodo(int tipo, String name) {
        this.name = name;
        this.paths = new ArrayList<>();
        this.tipo = tipo;
        this.loc = new Point(25, 25);
    }

    public void addPaths(Path p) {
        paths.add(p);
    }

    public void setPoin(Point p) {
        loc = p;
    }

    public ArrayList<Path> getPathList() {
        return paths;
    }

    public String getname() {
        return name;
    }

    public int getTipo() {
        return tipo;
    }

    public Point obtPos() {
        return loc;
    }

    public int getradio() {
        return r;
    }

    public void setobtPos(Point p) {
        loc = p;
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
        Ellipse2D el = new Ellipse2D.Double(loc.x - r, loc.y - r, 2 * r, 2 * r);
        g.fill(el);
        g.setColor(Color.BLACK);
        Ellipse2D e2 = new Ellipse2D.Double(loc.x - r, loc.y - r, 2 * r, 2 * r);
        g.draw(e2);
        g.setColor(Color.BLACK);
        g.setFont(TIPO_BASE);
        g.drawString(
                name,
                loc.x - fm.stringWidth(name) / 2 - 1,
                loc.y + fm.getAscent() / 2);
    }

    private static final Font TIPO_BASE
            = new Font(Font.SANS_SERIF, Font.PLAIN, 18);

    private final String name;
    private final int tipo;
    private final ArrayList<Path> paths;
    private Point loc;
    private int r = 20;
}
