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
            destino.obtPos().x, destino.obtPos().x + destino.getradio() + 10,
            destino.obtPos().x + destino.getradio() + 10, destino.obtPos().x,
            destino.obtPos().x,};
        int[] yPoints = {
            destino.obtPos().y - destino.getradio() / 2,
            destino.obtPos().y - destino.getradio() / 2,
            destino.obtPos().y - destino.getradio() - 10
            - (destino.getradio() / 2),
            destino.obtPos().y - destino.getradio() - 10
            - destino.getradio() / 2,
            destino.obtPos().y - destino.getradio() - 2};
        g.fillOval(destino.obtPos().x - 4, destino.obtPos().y
                - destino.getradio() - 5, 8, 8);
        g.setStroke(new BasicStroke(2.0f));
        g.drawPolyline(xPoints, yPoints, 5);
        g.drawString(
                String.format("%s", tag),
                xPoints[1] - destino.getradio() + 4,
                yPoints[2] - 15 + g.getFontMetrics().getAscent() / 2);
    }

    public void dibujavertical(Graphics2D g, Point p, Nodo destino) {
        if (destino.obtPos().y > p.y) {
            g.fillOval(destino.obtPos().x - 4,
                    destino.obtPos().y - destino.getradio() - 4, 8, 8);
            g.drawLine(p.x, p.y, destino.obtPos().x, destino.obtPos().y
                    - destino.getradio());
            g.drawString(
                    String.format("%s", tag),
                    destino.obtPos().x + 10,
                    destino.obtPos().y - destino.getradio()
                    - ((destino.obtPos().y - destino.getradio() - p.y)
                    / 2) + g.getFontMetrics().getAscent() / 2
            );
        } else {
            g.fillOval(destino.obtPos().x - 4, destino.obtPos().y
                    + destino.getradio() - 4, 8, 8);
            g.drawLine(p.x, p.y, destino.obtPos().x, destino.obtPos().y
                    + destino.getradio());
            g.drawString(
                    String.format("%s", tag),
                    destino.obtPos().x + 10,
                    destino.obtPos().y + destino.getradio()
                    + ((p.y - destino.obtPos().y - destino.getradio())
                    / 2) + g.getFontMetrics().getAscent() / 2
            );
        }
    }

    public void dibujahorizaontal(Graphics2D g, Point p, Nodo destino) {
        if (destino.obtPos().x > p.x) {
            g.fillOval(destino.obtPos().x - destino.getradio() - 4,
                    destino.obtPos().y - 4, 8, 8);
            g.drawLine(p.x, p.y, destino.obtPos().x - destino.getradio(),
                    destino.obtPos().y);
            g.drawString(
                    String.format("%s", tag), p.x + ((destino.obtPos().x
                    - destino.getradio() - p.x) / 2),
                    (destino.obtPos().y - g.getFontMetrics().getAscent() / 2)
                    - 10);
        } else {
            g.fillOval(destino.obtPos().x + destino.getradio() - 4,
                    destino.obtPos().y - 4, 8, 8);
            g.drawLine(p.x, p.y, destino.obtPos().x + destino.getradio(),
                    destino.obtPos().y);
            g.drawString(
                    String.format("%s", tag), p.x - ((p.x - destino.obtPos().x
                    - destino.getradio()) / 2), (destino.obtPos().y
                    - g.getFontMetrics().getAscent() / 2) - 10
            );
        }
    }

    public void dibujadiagonales(Graphics2D g, Point p, Nodo destino) {
        double d = Math.sqrt(Math.pow((destino.obtPos().x - p.x), 2)
                + Math.pow((destino.obtPos().y - p.y), 2));
        double c = (destino.obtPos().y - p.y) / d;
        System.out.println("angulo " + c);
        if (destino.obtPos().x > p.x && destino.obtPos().y > p.y) {
            diagonal_1(g, p, destino, c);
        } else {
            if (destino.obtPos().x > p.x && destino.obtPos().y < p.y) {
                diagonal_2(g, p, destino, c);
            } else {
                if (destino.obtPos().x < p.x && destino.obtPos().y < p.y) {
                    diagonal_3(g, p, destino, c);
                } else {
                    if (destino.obtPos().x < p.x && destino.obtPos().y > p.y) {
                        diagonal_4(g, p, destino, c);
                    }
                }
            }
        }
        System.out.println("destino [ " + (int) destinoRX
                + "," + (int) destinoRY + "]");
    }

    public void diagonal_1(Graphics2D g, Point p, Nodo destino, double c) {
        destinoRX = destino.obtPos().x - (destino.getradio() * Math.cos(c));
        System.out.println("x " + destinoRX);
        destinoRY = destino.obtPos().y - (destino.getradio() * Math.sin(c));
        System.out.println("y " + destinoRY);
        g.drawLine(p.x, p.y, (int) destinoRX, (int) destinoRY);
        g.fillOval((int) destinoRX - 4, (int) destinoRY - 4, 8, 8);
    }

    public void diagonal_2(Graphics2D g, Point p, Nodo destino, double c) {
        destinoRX = destino.obtPos().x - (destino.getradio() * Math.cos(c));
        System.out.println("x " + destinoRX);
        destinoRY = destino.obtPos().y - (destino.getradio() * Math.sin(c));
        System.out.println("y " + destinoRY);
        g.drawLine(p.x, p.y, (int) destinoRX, (int) destinoRY);
        g.fillOval((int) destinoRX - 4, (int) destinoRY - 4, 8, 8);
    }

    public void diagonal_3(Graphics2D g, Point p, Nodo destino, double c) {
        destinoRX = destino.obtPos().x + (destino.getradio() * Math.cos(c));
        System.out.println("x " + destinoRX);
        destinoRY = destino.obtPos().y - (destino.getradio() * Math.sin(c));
        System.out.println("y " + destinoRY);
        g.drawLine(p.x, p.y, (int) destinoRX, (int) destinoRY);
        g.fillOval((int) destinoRX - 4, (int) destinoRY - 4, 8, 8);
    }

    public void diagonal_4(Graphics2D g, Point p, Nodo destino, double c) {
        destinoRX = destino.obtPos().x + (destino.getradio() * Math.cos(c));
        System.out.println("x " + destinoRX);
        destinoRY = destino.obtPos().y - (destino.getradio() * Math.sin(c));
        System.out.println("y " + destinoRY);
        g.drawLine((int) destinoRX, (int) destinoRY, p.x, p.y);
        g.fillOval((int) destinoRX - 4, (int) destinoRY - 4, 8, 8);
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
