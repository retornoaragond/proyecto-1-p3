/*
Esteban Espinoza Fallas 402290345
José Fabio Alfaro Quesada 207580494
 */
package simulador.modelo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "path")
@XmlType(propOrder = {"tag", "destiny"})
public class Path {
    
    // <editor-fold desc="Constructores" defaultstate="collapsed">
    public Path(String s, int d) {
        this.tag = s;
        this.destiny = d;
    }

    public Path() {
    }
    // </editor-fold>
    
    // <editor-fold desc="Metodos" defaultstate="collapsed">
    @XmlElement(name = "destiny")
    public int getdestiny() {
        return destiny;
    }

    public void setdestiny(int n) {
        this.destiny = n;
    }

    @XmlAttribute(name = "tag")
    public String gettag() {
        return tag;
    }

    public void settag(String t) {
        this.tag = t;
    }

    public boolean isInTag(char c) {
        for (int i = 0; i < tag.length(); i++) {
            if (tag.charAt(i) == c) {
                return true;
            }
        }
        return false;
    }
    
    public Nodo find(ArrayList<Nodo> p) {
        return p.get(destiny);
    }
    
    // <editor-fold desc="Dibujar" defaultstate="collapsed">
    public void dibujar(Graphics2D g, Point p, ArrayList<Nodo> l) {
        Nodo destino = find(l);
        g.setColor(Color.BLACK);
        g.setFont(TIPO_BASE);
        g.setStroke(new BasicStroke(2.0f));
        if (p.x == destino.getx() && destino.gety() == p.y) {
            dibujaciclo(g, p, destino);
        } else {
            if ((p.x == destino.getx() && destino.gety() != p.y)) {
                dibujavertical(g, p, destino);
            } else {
                if (p.x != destino.getx() && destino.gety() == p.y) {
                    dibujahorizaontal(g, p, destino);
                } else {
                    dibujadiagonales(g, p, destino);
                }
            }
        }
    }
    
    // <editor-fold desc="Ciclo" defaultstate="collapsed">
    public void dibujaciclo(Graphics2D g, Point p, Nodo destino) {
        int[] xPoints = {
            destino.getx(),
            destino.getx() + destino.getradio() + 10,
            destino.getx() + destino.getradio() + 10,
            destino.getx(),
            destino.getx(),};
        int[] yPoints = {
            destino.gety() - destino.getradio() / 2,
            destino.gety() - destino.getradio() / 2,
            destino.gety() - destino.getradio() - 10
            - (destino.getradio() / 2),
            destino.gety() - destino.getradio() - 10
            - destino.getradio() / 2,
            destino.gety() - destino.getradio() - 2};
        g.setStroke(new BasicStroke(2.0f));
        g.drawPolyline(xPoints, yPoints, 5);
        g.drawString(
                String.format("%s", tag),
                xPoints[1] - destino.getradio() + 4,
                yPoints[2] - 15 + g.getFontMetrics().getAscent() / 2);
        Point aux = new Point(xPoints[3], yPoints[3]);
        Point aux1 = new Point(xPoints[4], yPoints[4]);
        dibujarFlecha(aux, aux1, g);
    }
    // </editor-fold>
    
    // <editor-fold desc="Vertical" defaultstate="collapsed">
    public void dibujavertical(Graphics2D g, Point p, Nodo destino) {
        if (destino.gety() > p.y) {
            g.drawLine(p.x, p.y, destino.getx(), destino.gety()
                    - destino.getradio());
            g.drawString(
                    String.format("%s", tag),
                    destino.getx() + 10,
                    destino.gety() - destino.getradio()
                    - ((destino.gety() - destino.getradio() - p.y)
                    / 2) + g.getFontMetrics().getAscent() / 2
            );
            Point aux = new Point(destino.getx(),
                    destino.gety() - destino.getradio());
            dibujarFlecha(p, aux, g);
        } else {
            g.drawLine(p.x, p.y, destino.getx(), destino.gety()
                    + destino.getradio());
            g.drawString(
                    String.format("%s", tag),
                    destino.getx() + 10,
                    destino.gety() + destino.getradio()
                    + ((p.y - destino.gety() - destino.getradio())
                    / 2) + g.getFontMetrics().getAscent() / 2
            );
            Point aux = new Point(destino.getx(),
                    destino.gety() + destino.getradio());
            dibujarFlecha(p, aux, g);
        }
    }
    // </editor-fold>
    
    // <editor-fold desc="Horizaontal" defaultstate="collapsed">
    public void dibujahorizaontal(Graphics2D g, Point p, Nodo destino) {
        if (destino.getx() > p.x) {
            g.drawLine(p.x, p.y, destino.getx() - destino.getradio(),
                    destino.gety());
            g.drawString(
                    String.format("%s", tag), p.x + ((destino.getx()
                    - destino.getradio() - p.x) / 2),
                    (destino.gety() - g.getFontMetrics().getAscent() / 2)
                    - 10);
            Point aux = new Point(destino.getx() - destino.getradio(),
                    destino.gety());
            dibujarFlecha(p, aux, g);
        } else {
            g.drawLine(p.x, p.y, destino.getx() + destino.getradio(),
                    destino.gety());
            g.drawString(
                    String.format("%s", tag), p.x - ((p.x - destino.getx()
                    - destino.getradio()) / 2), (destino.gety()
                    - g.getFontMetrics().getAscent() / 2) - 10
            );
            Point aux = new Point(destino.getx() + destino.getradio(),
                    destino.gety());
            dibujarFlecha(p, aux, g);
        }
    }
    // </editor-fold>
    
    // <editor-fold desc="Diagonales" defaultstate="collapsed">
    public void dibujadiagonales(Graphics2D g, Point p, Nodo destino) {
        double d = Math.sqrt(Math.pow((destino.getx() - p.x), 2)
                + Math.pow((destino.gety() - p.y), 2));
        double c = (destino.gety() - p.y) / d;
        System.out.println("angulo " + c);
        if (destino.getx() > p.x && destino.gety() > p.y) {
            diagonal_1(g, p, destino, c, d);
        } else {
            if (destino.getx() > p.x && destino.gety() < p.y) {
                diagonal_2(g, p, destino, c, d);
            } else {
                if (destino.getx() < p.x && destino.gety() < p.y) {
                    diagonal_3(g, p, destino, c, d);
                } else {
                    if (destino.getx() < p.x && destino.gety() > p.y) {
                        diagonal_4(g, p, destino, c, d);
                    }
                }
            }
        }
        System.out.println("destino [ " + (int) destinoRX
                + "," + (int) destinoRY + "]");
    }
    
    // <editor-fold desc="Diagonal_1" defaultstate="collapsed">
    public void diagonal_1(Graphics2D g, Point p, Nodo destino, double c, double d) {
        destinoRX = destino.getx() - (destino.getradio() * Math.cos(c));
        System.out.println("x " + destinoRX);
        destinoRY = destino.gety() - (destino.getradio() * Math.sin(c));
        System.out.println("y " + destinoRY);
        g.drawLine(p.x, p.y, (int) destinoRX, (int) destinoRY);
        Point aux = new Point((int) destinoRX, (int) destinoRY);
        dibujarFlecha(p, aux, g);
        tagdiagonal(p, aux, g, d);
    }
    // </editor-fold>
    
    // <editor-fold desc="Diagonal_2" defaultstate="collapsed">
    public void diagonal_2(Graphics2D g, Point p, Nodo destino, double c, double d) {
        destinoRX = destino.getx() - (destino.getradio() * Math.cos(c));
        System.out.println("x " + destinoRX);
        destinoRY = destino.gety() - (destino.getradio() * Math.sin(c));
        System.out.println("y " + destinoRY);
        g.drawLine(p.x, p.y, (int) destinoRX, (int) destinoRY);
        Point aux = new Point((int) destinoRX, (int) destinoRY);
        dibujarFlecha(p, aux, g);
        tagdiagonal(p, aux, g, d);
    }
    // </editor-fold>
    
    // <editor-fold desc="Diagonal_3" defaultstate="collapsed">
    public void diagonal_3(Graphics2D g, Point p, Nodo destino, double c, double d) {
        destinoRX = destino.getx() + (destino.getradio() * Math.cos(c));
        System.out.println("x " + destinoRX);
        destinoRY = destino.gety() - (destino.getradio() * Math.sin(c));
        System.out.println("y " + destinoRY);
        g.drawLine(p.x, p.y, (int) destinoRX, (int) destinoRY);
        Point aux = new Point((int) destinoRX, (int) destinoRY);
        dibujarFlecha(p, aux, g);
        tagdiagonal(p, aux, g, d);
    }
    // </editor-fold>
    
    // <editor-fold desc="Diagonal_4" defaultstate="collapsed">
    public void diagonal_4(Graphics2D g, Point p, Nodo destino, double c, double d) {
        destinoRX = destino.getx() + (destino.getradio() * Math.cos(c));
        System.out.println("x " + destinoRX);
        destinoRY = destino.gety() - (destino.getradio() * Math.sin(c));
        System.out.println("y " + destinoRY);
        g.drawLine((int) destinoRX, (int) destinoRY, p.x, p.y);
        Point aux = new Point((int) destinoRX, (int) destinoRY);
        dibujarFlecha(p, aux, g);
        tagdiagonal(p, aux, g, d);
    }
    // </editor-fold>
    
    // </editor-fold>
    
    // <editor-fold desc="Flecha" defaultstate="collapsed">
    public void dibujarFlecha(Point punto1, Point punto2, Graphics2D g) {
        double ang, angSep = 45.0;
        double tx, ty;
        int dist = 10;
        ty = -(punto1.y - punto2.y) * 1.0;
        tx = (punto1.x - punto2.x) * 1.0;
        ang = Math.atan(ty / tx);
        if (tx < 0) {
            ang += Math.PI;
        }
        Point p1 = new Point(), p2 = new Point(), punto = punto2;
        p1.x = (int) (punto.x + dist * Math.cos(ang - Math.toRadians(angSep)));
        p1.y = (int) (punto.y - dist * Math.sin(ang - Math.toRadians(angSep)));
        p2.x = (int) (punto.x + dist * Math.cos(ang + Math.toRadians(angSep)));
        p2.y = (int) (punto.y - dist * Math.sin(ang + Math.toRadians(angSep)));
        g.drawLine(p1.x, p1.y, punto.x, punto.y);
        g.drawLine(p2.x, p2.y, punto.x, punto.y);
    }
    // </editor-fold>

    // <editor-fold desc="tag en Diagonal" defaultstate="collapsed">
    public void tagdiagonal(Point punto1, Point punto2, Graphics2D g, double d) {
        double ang, angSep = 1.0;
        double tx, ty;
        double dist = Math.sqrt(Math.pow((d / 2), 2) + Math.pow((5), 2)) - 20;
        ty = -(punto1.y - punto2.y) * 1.0;
        tx = (punto1.x - punto2.x) * 1.0;
        ang = Math.atan(ty / tx);
        if (tx < 0) {
            ang += Math.PI;
        }
        Point p1 = new Point(), punto = punto2;
        p1.x = (int) (punto.x + dist * Math.cos(ang - Math.toRadians(angSep)));
        p1.y = (int) (punto.y - dist * Math.sin(ang - Math.toRadians(angSep)));
        g.drawString(
                String.format("%s", tag), p1.x,
                (p1.y - g.getFontMetrics().getAscent() / 2) - 10);
    }
    // </editor-fold>
    
    // </editor-fold>
    
    // </editor-fold>
    
    // <editor-fold desc="Atributos" defaultstate="collapsed">

    double destinoRX;
    double destinoRY;
    private String tag;
    private int destiny;
    private static final Font TIPO_BASE
            = new Font(Font.SANS_SERIF, Font.ITALIC, 15);
    // </editor-fold>
}
