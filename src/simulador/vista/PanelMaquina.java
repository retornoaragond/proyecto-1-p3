package simulador.vista;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import simulador.modelo.Simulator;

public class PanelMaquina extends JPanel {
    public PanelMaquina() {
        super();
        this.modelo = null;
        configurar();
    }

    private void configurar() {
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public void definirModelo(Simulator modelo) {
        this.modelo = modelo;
    }

    @Override
    public void paintComponent(Graphics bg) {
        super.paintComponent(bg);
        if(modelo != null) {
            modelo.dibujar((Graphics2D)bg);
        }
    }
    
    private Simulator modelo;
}
