package simulador;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import simulador.control.Control_Simulator;
import simulador.modelo.Simulator;
import simulador.vista.Ventana_Simulador;

public class Simulador {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
            JFrame.setDefaultLookAndFeelDecorated(true);
        } catch (ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | UnsupportedLookAndFeelException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        SwingUtilities.invokeLater(() -> {
            mostrarInterfaz();
        });
    }

    public static void mostrarInterfaz() {
        Simulator modelo = new Simulator();
        Control_Simulator gestorPrincipal = new Control_Simulator(modelo);
        Ventana_Simulador ventanaPrincipal = new Ventana_Simulador(gestorPrincipal);
        ventanaPrincipal.init();
    }
}
