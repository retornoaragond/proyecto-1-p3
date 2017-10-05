
package simulador.modelo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;



public class Path {
    
    public Path(String s, int d){
        this.tag = s;
        this.destino = d;
    }
    
    public Path(String s){
        this(s, -1);
    }

    public void setDestiny(int n){
        this.destino = n;
    }
    
    public String getTag(){
        return tag;
    }
    
    public int getDestiny(){
        return destino;
    }
    
    public boolean isInTag(char c){
        for(int i = 0; i < tag.length(); i++){
            if(tag.charAt(i) == c){
                return true;
            }
        }
        return false;
    }
    public void dibujar( Graphics2D g ,Point p) {
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(2.0f));
        if (p.x == destino.obtPos().x && destino.obtPos().y == p.y) {
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
            g.fillOval(destino.obtPos().x - 4, destino.obtPos().y - destino.getradio() - 5, 8, 8);
//            g.drawLine(destino.obtPos().x,
//                    destino.obtPos().y - destino.getradio() - 1,
//                    destino.obtPos().x - 5,
//                    destino.obtPos().y - destino.getradio() - 7);
//            g.drawLine(destino.obtPos().x,
//                    destino.obtPos().y - destino.getradio() - 1,
//                    destino.obtPos().x + 5,
//                    destino.obtPos().y - destino.getradio() - 7);
            g.setStroke(new BasicStroke(2.0f));
            g.drawPolyline(xPoints, yPoints, 5);
            g.drawString(
                    String.format("%s", tag),
                    xPoints[1] - destino.getradio() + 4,
                    yPoints[2] - 15 + g.getFontMetrics().getAscent() / 2);
        } else {
            if ((p.x == destino.obtPos().x && destino.obtPos().y != p.y)) {
                if (destino.obtPos().y > p.y) {
                    g.fillOval(destino.obtPos().x-4,
                            destino.obtPos().y - destino.getradio()-4, 8, 8);
//                    g.drawLine(
//                            destino.obtPos().x,
//                            destino.obtPos().y - destino.getradio() - 1,
//                            destino.obtPos().x - 5,
//                            destino.obtPos().y - destino.getradio() - 7);
//                    g.drawLine(destino.obtPos().x,
//                            destino.obtPos().y - destino.getradio() - 1,
//                            destino.obtPos().x + 5,
//                            destino.obtPos().y - destino.getradio() - 7);
                    g.drawLine(p.x, p.y, destino.obtPos().x, destino.obtPos().y
                            - destino.getradio());

                } else {
                    g.fillOval(destino.obtPos().x-4, destino.obtPos().y + destino.getradio()-4, 8, 8);
//                    g.drawLine(destino.obtPos().x,
//                            destino.obtPos().y + destino.getradio() + 1,
//                            destino.obtPos().x - 5,
//                            destino.obtPos().y + destino.getradio() + 7);
//                    g.drawLine(destino.obtPos().x,
//                            destino.obtPos().y + destino.getradio() + 1,
//                            destino.obtPos().x + 5,
//                            destino.obtPos().y + destino.getradio() + 7);
                    g.drawLine(p.x, p.y, destino.obtPos().x, destino.obtPos().y
                            + destino.getradio());
                }
            } else {
                if (p.x != destino.obtPos().x && destino.obtPos().y == p.y) {
                    if (destino.obtPos().x > p.x) {
                        g.fillOval(destino.obtPos().x - destino.getradio()-4, destino.obtPos().y-4, 8, 8);
//                        g.drawLine(destino.obtPos().x - destino.getradio(),
//                                destino.obtPos().y,
//                                destino.obtPos().x - destino.getradio() - 7,
//                                destino.obtPos().y - 5);
//                        g.drawLine(destino.obtPos().x - destino.getradio(),
//                                destino.obtPos().y,
//                                destino.obtPos().x - destino.getradio() - 7,
//                                destino.obtPos().y + 5);
                        g.drawLine(p.x, p.y, destino.obtPos().x - destino.getradio(),
                                destino.obtPos().y);
                    } else {
                        g.fillOval(destino.obtPos().x + destino.getradio() -4, destino.obtPos().y - 4, 8, 8);
//                        g.drawLine(destino.obtPos().x + destino.getradio(),
//                                destino.obtPos().y,
//                                destino.obtPos().x + destino.getradio() + 7,
//                                destino.obtPos().y - 5);
//                        g.drawLine(destino.obtPos().x + destino.getradio(),
//                                destino.obtPos().y,
//                                destino.obtPos().x + destino.getradio() + 7,
//                                destino.obtPos().y + 5);
                        g.drawLine(p.x, p.y, destino.obtPos().x + destino.getradio(),
                                destino.obtPos().y);
                    }
                } else {
                    double d = Math.sqrt(Math.pow((destino.obtPos().x - p.x), 2)
                            + Math.pow((destino.obtPos().y - p.y), 2));
                    double c = (destino.obtPos().y - p.y) / d;
                    System.out.println("angulo " + c);
                    if (destino.obtPos().x > p.x && destino.obtPos().y > p.y) {
                        destinoRX = destino.obtPos().x - destino.getradio() * Math.cos(c);
                        System.out.println("x " + destinoRX);
                        destinoRY = destino.obtPos().y - destino.getradio() * Math.sin(c);
                        System.out.println("y " + destinoRY);
                        g.drawLine(p.x, p.y, (int) destinoRX, (int) destinoRY);
                        g.fillOval((int) destinoRX - 4, (int) destinoRY - 4, 8, 8);
                    } else {
                        if (destino.obtPos().x > p.x && destino.obtPos().y < p.y) {
                            destinoRX = destino.obtPos().x - destino.getradio() * Math.cos(c);
                            System.out.println("x " + destinoRX);
                            destinoRY = destino.obtPos().y - destino.getradio() * Math.sin(c);
                            System.out.println("y " + destinoRY);
                            g.drawLine(p.x, p.y, (int) destinoRX, (int) destinoRY);
                            g.fillOval((int) destinoRX - 4, (int) destinoRY - 4, 8, 8);
                        } else {
                            if (destino.obtPos().x < p.x && destino.obtPos().y < p.y) {
                                destinoRX = destino.obtPos().x + destino.getradio() * Math.cos(c);
                                System.out.println("x " + destinoRX);
                                destinoRY = destino.obtPos().y - destino.getradio() * Math.sin(c);
                                System.out.println("y " + destinoRY);
                                g.drawLine(p.x, p.y, (int) destinoRX, (int) destinoRY);
                                g.fillOval((int) destinoRX - 4, (int) destinoRY - 4, 8, 8);
                            } else {
                                if (destino.obtPos().x < p.x && destino.obtPos().y > p.y) {
                                    destinoRX = destino.obtPos().x + destino.getradio() * Math.cos(c);
                                    System.out.println("x " + destinoRX);
                                    destinoRY = destino.obtPos().y - destino.getradio() * Math.sin(c);
                                    System.out.println("y " + destinoRY);
                                    g.drawLine((int) destinoRX, (int) destinoRY, p.x, p.y);
                                    g.fillOval((int) destinoRX - 4, (int) destinoRY - 4, 8, 8);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    double destinoRX;
    double destinoRY;
    
    private String tag;
    private int destino;
}