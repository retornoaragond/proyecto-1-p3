/*
Esteban Espinoza Fallas 402290345
José Fabio Alfaro Quesada 207580494
*/
package simulador.vista;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static java.awt.event.MouseEvent.BUTTON1;
import static java.awt.event.MouseEvent.BUTTON3;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.filechooser.FileNameExtensionFilter;
import simulador.control.Control_Simulator;
import simulador.modelo.Simulator;

public class Ventana_Simulador extends JFrame
        implements Observer {

    // <editor-fold desc="Constructores" defaultstate="collapsed">
    public Ventana_Simulador(Control_Simulator nuevoGestor) {
        super("MEF");
        System.out.println("Creando ventana principal..");
        System.out.println("Asociando clase de control..");
        this.gestorPrincipal = nuevoGestor;
        configurar();
    }

    // </editor-fold>
    // <editor-fold desc="Configuración" defaultstate="collapsed" >
    private void configurar() {
        gestorPrincipal.registrar(this);
        ajustarComponentes(getContentPane());
        setResizable(true);
        setMinimumSize(new Dimension(400, 300));
        setLocationRelativeTo(null);
        pack();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                cerrarVentana();
            }
        });
    }

    private void ajustarComponentes(Container c) {
        c.setLayout(new BorderLayout());
        c.add(BorderLayout.CENTER,
                panelPrincipal = new PanelMaquina());
        System.out.println("Configurando componentes..");
        ajustarMenus(c);
        
        // <editor-fold desc="Archivo" defaultstate="collapsed">
        itemRecuperar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                abrir_proyecto();
            }
        });

        itemGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                guardar_proyecto();
                System.out.println("guardando la maquina ventana..");

            }
        });

        itemLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                gestorPrincipal.limpiar();
                System.out.println("limpiando la maquina ventana..");
            }
        });
        // </editor-fold>
        
        // <editor-fold desc="Estados" defaultstate="collapsed">
        itemInicial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String nombreInicial = JOptionPane.showInputDialog(
                        "Nombre de Estado Inicial:");
                if (nombreInicial != null && !"".equals(nombreInicial)) {
                    gestorPrincipal.crea_estado(1, nombreInicial);
                    System.out.println("Creando un estado inicial ventana..");
                } else {
                    JOptionPane.showMessageDialog(null, "Debe asignarle un nombre al nuevo estado.");
                }
            }
        });

        itemIntermedio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String nombreIntermedio = JOptionPane.showInputDialog(
                        "Nombre de Estado Intermedio:");
                if (nombreIntermedio != null && !"".equals(nombreIntermedio)) {
                    gestorPrincipal.crea_estado(2, nombreIntermedio);
                    System.out.println("Creando un estado intermedio ventana..");
                } else {
                    JOptionPane.showMessageDialog(null, "Debe asignarle un nombre al nuevo estado.");
                }

            }
        });

        itemFinal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String nombreFinal = JOptionPane.showInputDialog(
                        "Nombre de Estado Final:");
                if (nombreFinal != null && !"".equals(nombreFinal)) {
                    gestorPrincipal.crea_estado(3, nombreFinal);
                    System.out.println("Creando un estado final ventana..");
                } else {
                    JOptionPane.showMessageDialog(null, "Debe asignarle un nombre al nuevo estado.");
                }
            }
        });
        // </editor-fold>
        
        // <editor-fold desc="Verificar" defaultstate="collapsed">
        itemHilera.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                hilera = JOptionPane.showInputDialog("Hilera:");
                if (!"".equals(hilera)) {
                    gestorPrincipal.verificar_hilera(hilera);
                    System.out.println("verificando hilera ventana..");
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Para probar una hilera debe ingresar algo para ser provado");
                }
            }
        });
        // </editor-fold>
        
        // <editor-fold desc="Mouse" defaultstate="collapsed">
        panelPrincipal.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == BUTTON1) {
                    gestorPrincipal.seleccionar(e.getPoint());
                    System.out.printf("selecionado [%d,%d]\n",
                            e.getPoint().x, e.getPoint().y);
                } else {
                    if (e.getButton() == BUTTON3) {
                        gestorPrincipal.selorigen(e.getPoint());
                    }
                    System.out.printf("origen [%d,%d]\n",
                            e.getPoint().x, e.getPoint().y);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == BUTTON1) {
                    gestorPrincipal.desseleccionar();
                    System.out.printf("deseleccionado [%d,%d]\n",
                            e.getPoint().x, e.getPoint().y);
                } else {
                    gestorPrincipal.seldest(e.getPoint());
                    gestorPrincipal.agregarArista();
                    System.out.printf("delineado [%d,%d]\n",
                            e.getPoint().x, e.getPoint().y);
                    gestorPrincipal.desselorigen();
                    System.out.println("reset origen");
                    gestorPrincipal.desdest();
                    System.out.println("reset destino");
                    repaint();
                }
            }

        });

        panelPrincipal.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (e.isShiftDown()) {
                    gestorPrincipal.arrastrar(e.getPoint());
                } else {
                    gestorPrincipal.predest(e.getPoint());
                    System.out.printf("delineado mover [%d,%d]\n",
                            e.getPoint().x, e.getPoint().y);
                }
            }
        });

        // </editor-fold>
    }

    private void ajustarMenus(Container c) {
        menuPrincipal = new JMenuBar();
        menuPrincipal.add(menuArchivo = new JMenu("Archivo"));
        menuArchivo.add(itemGuardar = new JMenuItem("Guardar"));
        menuArchivo.add(itemRecuperar = new JMenuItem("Recuperar"));
        menuArchivo.add(new JSeparator());
        menuArchivo.add(itemLimpiar = new JMenuItem("Limpiar"));

        menuPrincipal.add(menuEstados = new JMenu("Estados"));
        menuEstados.add(itemInicial = new JMenuItem("Inicial"));
        menuEstados.add(itemIntermedio = new JMenuItem("Intermedio"));
        menuEstados.add(itemFinal = new JMenuItem("Final"));

        menuPrincipal.add(menuVerificar = new JMenu("Verificar"));
        menuVerificar.add(itemHilera = new JMenuItem("Hilera"));
        setJMenuBar(menuPrincipal);
    }

    public void init() {
        System.out.println("Inicializando interfaz..");
        gestorPrincipal.registrar(this);
        setVisible(true);

    }

    @Override
    public void update(Observable obs, Object arg) {
        PanelMaquina pm = (PanelMaquina) panelPrincipal;
        pm.definirModelo((Simulator) obs);
        pm.repaint();
    }

    // </editor-fold>
    
    // <editor-fold desc="Métodos" defaultstate="collapsed">
    public void cerrarVentana() {
        if (JOptionPane.showConfirmDialog(this,
                "¿Desea cerrar la aplicación?", "Confirmar",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
            System.out.println("Cerrando ventana principal..");
            gestorPrincipal.cerrarAplicacion();
        }
    }

    public void abrir_proyecto() {
        JFileChooser file = new JFileChooser();
        file.setFileFilter(filter);
        int opcion = file.showOpenDialog(this);
        if (opcion == JFileChooser.APPROVE_OPTION) {
            ruta_Archivo_open = file.getSelectedFile().toString();
        }
        if (ruta_Archivo_open != null) {
            System.out.println("ruta abrir: " + ruta_Archivo_open);
            gestorPrincipal.abrirarchivo(ruta_Archivo_open);
        }
    }

    public void guardar_proyecto() {
        JFileChooser file_open = new JFileChooser();
        file_open.setFileFilter(filter);
        int opcion = file_open.showSaveDialog(this);
        if (opcion == JFileChooser.APPROVE_OPTION) {
            ruta_Archivo_save = file_open.getSelectedFile().toString();

        }
        if (ruta_Archivo_save != null) {
            System.out.println("ruta guardar: " + ruta_Archivo_save);
            gestorPrincipal.guardararchivo(ruta_Archivo_save);
        }
    }
    // </editor-fold>
    
    // <editor-fold desc="Atributos" defaultstate="collapsed" >
    private final Control_Simulator gestorPrincipal;

    private JMenuBar menuPrincipal;
    private JPanel panelPrincipal;
    private JMenu menuArchivo;
    private JMenu menuEstados;
    private JMenu menuVerificar;

    private JMenuItem itemGuardar;
    private JMenuItem itemRecuperar;
    private JMenuItem itemLimpiar;
    private JMenuItem itemInicial;
    private JMenuItem itemIntermedio;
    private JMenuItem itemFinal;
    private JMenuItem itemHilera;

    private String ruta_Archivo_open;
    private String ruta_Archivo_save;
    private String hilera;

    private final FileNameExtensionFilter filter
            = new FileNameExtensionFilter(
                    "Archivos .xml", "xml"
            );
    // </editor-fold>
}
