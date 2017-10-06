package simulador.modelo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class Path {

    public Path(String s, int d) {
        this.tag = s;
        this.destinoi = d;
    }

    public Path(String s) {
        this(s, -1);
    }

    public void setDestiny(int n) {
        this.destinoi = n;
    }

    public String getTag() {
        return tag;
    }

    public int getDestiny() {
        return destinoi;
    }

    public boolean isInTag(char c) {
        for (int i = 0; i < tag.length(); i++) {
            if (tag.charAt(i) == c) {
                return true;
            }
        }
        return false;
    }

    public void dibujar(Graphics2D g, Point p, ArrayList<Nodo> l) {
        Nodo destino = find(l);
        g.setColor(Color.BLACK);
        g.setFont(TIPO_BASE);
        g.setStroke(new BasicStroke(2.0f));

        if (p.x == destino.obtPos().x && destino.obtPos().y == p.y) {
            dibujaciclo(g, p, destino);
        } else {
            if ((p.x == destino.obtPos().x && destino.obtPos().y != p.y)) {
                dibujavertical(g, p, destino);
            } else {
                if (p.x != destino.obtPos().x && destino.obtPos().y == p.y) {
                    dibujahorizaontal(g, p, destino);
                } else {
                    dibujadiagonales(g, p, destino);
                }
            }
        }
    }

    public void dibujaciclo(Graphics2D g, Point p, Nodo destino) {
        int[] xPoints = {
            destino.obtPos().x,
            destino.obtPos().x + destino.getradio() + 10,
            destino.obtPos().x + destino.getradio() + 10,
            destino.obtPos().x,
            destino.obtPos().x,};
        int[] yPoints = {
            destino.obtPos().y - destino.getradio() / 2,
            destino.obtPos().y - destino.getradio() / 2,
            destino.obtPos().y - destino.getradio() - 10
            - (destino.getradio() / 2),
            destino.obtPos().y - destino.getradio() - 10
            - destino.getradio() / 2,
            destino.obtPos().y - destino.getradio() - 2};
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

    public void dibujavertical(Graphics2D g, Point p, Nodo destino) {
        if (destino.obtPos().y > p.y) {
            g.drawLine(p.x, p.y, destino.obtPos().x, destino.obtPos().y
                    - destino.getradio());
            g.drawString(
                    String.format("%s", tag),
                    destino.obtPos().x + 10,
                    destino.obtPos().y - destino.getradio()
                    - ((destino.obtPos().y - destino.getradio() - p.y)
                    / 2) + g.getFontMetrics().getAscent() / 2
            );
            Point aux = new Point(destino.obtPos().x,
                    destino.obtPos().y - destino.getradio());
            dibujarFlecha(p, aux, g);
        } else {
            g.drawLine(p.x, p.y, destino.obtPos().x, destino.obtPos().y
                    + destino.getradio());
            g.drawString(
                    String.format("%s", tag),
                    destino.obtPos().x + 10,
                    destino.obtPos().y + destino.getradio()
                    + ((p.y - destino.obtPos().y - destino.getradio())
                    / 2) + g.getFontMetrics().getAscent() / 2
            );
            Point aux = new Point(destino.obtPos().x,
                    destino.obtPos().y + destino.getradio());
            dibujarFlecha(p, aux, g);
        }
    }

    public void dibujahorizaontal(Graphics2D g, Point p, Nodo destino) {
        if (destino.obtPos().x > p.x) {
            g.drawLine(p.x, p.y, destino.obtPos().x - destino.getradio(),
                    destino.obtPos().y);
            g.drawString(
                    String.format("%s", tag), p.x + ((destino.obtPos().x
                    - destino.getradio() - p.x) / 2),
                    (destino.obtPos().y - g.getFontMetrics().getAscent() / 2)
                    - 10);
            Point aux = new Point(destino.obtPos().x - destino.getradio(),
                    destino.obtPos().y);
            dibujarFlecha(p, aux, g);
        } else {
            g.drawLine(p.x, p.y, destino.obtPos().x + destino.getradio(),
                    destino.obtPos().y);
            g.drawString(
                    String.format("%s", tag), p.x - ((p.x - destino.obtPos().x
                    - destino.getradio()) / 2), (destino.obtPos().y
                    - g.getFontMetrics().getAscent() / 2) - 10
            );
            Point aux = new Point(destino.obtPos().x + destino.getradio(),
                    destino.obtPos().y);
            dibujarFlecha(p, aux, g);
        }

    }

    public void dibujadiagonales(Graphics2D g, Point p, Nodo destino) {
        double d = Math.sqrt(Math.pow((destino.obtPos().x - p.x), 2)
                + Math.pow((destino.obtPos().y - p.y), 2));
        double c = (destino.obtPos().y - p.y) / d;
        System.out.println("angulo " + c);
        if (destino.obtPos().x > p.x && destino.obtPos().y > p.y) {
            diagonal_1(g, p, destino, c, d);
        } else {
            if (destino.obtPos().x > p.x && destino.obtPos().y < p.y) {
                diagonal_2(g, p, destino, c, d);
            } else {
                if (destino.obtPos().x < p.x && destino.obtPos().y < p.y) {
                    diagonal_3(g, p, destino, c, d);
                } else {
                    if (destino.obtPos().x < p.x && destino.obtPos().y > p.y) {
                        diagonal_4(g, p, destino, c, d);
                    }
                }
            }
        }
        System.out.println("destino [ " + (int) destinoRX
                + "," + (int) destinoRY + "]");
    }

    public void diagonal_1(Graphics2D g, Point p, Nodo destino, double c, double d) {
        destinoRX = destino.obtPos().x - (destino.getradio() * Math.cos(c));
        System.out.println("x " + destinoRX);
        destinoRY = destino.obtPos().y - (destino.getradio() * Math.sin(c));
        System.out.println("y " + destinoRY);
        g.drawLine(p.x, p.y, (int) destinoRX, (int) destinoRY);
        Point aux = new Point((int) destinoRX, (int) destinoRY);
        dibujarFlecha(p, aux, g);
        tagdiagonal(p, aux, g, d);
    }

    public void diagonal_2(Graphics2D g, Point p, Nodo destino, double c, double d) {
        destinoRX = destino.obtPos().x - (destino.getradio() * Math.cos(c));
        System.out.println("x " + destinoRX);
        destinoRY = destino.obtPos().y - (destino.getradio() * Math.sin(c));
        System.out.println("y " + destinoRY);
        g.drawLine(p.x, p.y, (int) destinoRX, (int) destinoRY);
        Point aux = new Point((int) destinoRX, (int) destinoRY);
        dibujarFlecha(p, aux, g);
        tagdiagonal(p, aux, g, d);
    }

    public void diagonal_3(Graphics2D g, Point p, Nodo destino, double c, double d) {
        destinoRX = destino.obtPos().x + (destino.getradio() * Math.cos(c));
        System.out.println("x " + destinoRX);
        destinoRY = destino.obtPos().y - (destino.getradio() * Math.sin(c));
        System.out.println("y " + destinoRY);
        g.drawLine(p.x, p.y, (int) destinoRX, (int) destinoRY);
        Point aux = new Point((int) destinoRX, (int) destinoRY);
        dibujarFlecha(p, aux, g);
        tagdiagonal(p, aux, g, d);
    }

    public void diagonal_4(Graphics2D g, Point p, Nodo destino, double c, double d) {
        destinoRX = destino.obtPos().x + (destino.getradio() * Math.cos(c));
        System.out.println("x " + destinoRX);
        destinoRY = destino.obtPos().y - (destino.getradio() * Math.sin(c));
        System.out.println("y " + destinoRY);
        g.drawLine((int) destinoRX, (int) destinoRY, p.x, p.y);
        Point aux = new Point((int) destinoRX, (int) destinoRY);
        dibujarFlecha(p, aux, g);
        tagdiagonal(p, aux, g, d);
    }

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

    public void tagdiagonal(Point punto1, Point punto2, Graphics2D g, double d) {
        double ang, angSep = 1.0;
        double tx, ty;
        double dist = Math.sqrt(Math.pow((d / 2), 2) + Math.pow((5), 2))-20;
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
    
    public Nodo find(ArrayList<Nodo> p) {
        return p.get(destinoi);
    }

    double destinoRX;
    double destinoRY;
    private String tag;
    private int destinoi;
    private static final Font TIPO_BASE
            = new Font(Font.SANS_SERIF, Font.ITALIC, 15);
}
