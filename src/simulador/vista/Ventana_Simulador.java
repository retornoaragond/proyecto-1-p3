package simulador.vista;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JSeparator;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.filechooser.FileNameExtensionFilter;
import simulador.control.Control_Simulator;

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
        System.out.println("Configurando componentes..");
        ajustarMenus(c);
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
            }
        });

        itemLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                gestorPrincipal.limpiar();
            }
        });
        // </editor-fold>
        // <editor-fold desc="Estados" defaultstate="collapsed">
        itemInicial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //llamar al metodo de eliminar la maquina en modelo
            }
        });

        itemIntermedio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //llamar al metodo de eliminar la maquina en modelo
            }
        });

        itemFinal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //llamar al metodo de eliminar la maquina en modelo
            }
        });
        // </editor-fold>
        // <editor-fold desc="Verificar" defaultstate="collapsed">
        itemHilera.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //llamar al metodo de eliminar la maquina en modelo
            }
        });
        // </editor-fold>
    }

    public void init() {
        System.out.println("Inicializando interfaz..");
        setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            //String nombrearchivo = file.getSelectedFile().getPath();
            ruta_Archivo_open = file.getSelectedFile().toString();
        }
        gestorPrincipal.abrirarchivo(ruta_Archivo_open);
    }

    public void guardar_proyecto() {
        JFileChooser file_open = new JFileChooser();
        file_open.setFileFilter(filter);
        int opcion = file_open.showSaveDialog(this);
        if (opcion == JFileChooser.APPROVE_OPTION) {

            ruta_Archivo_save = file_open.getSelectedFile().toString();
        }
        gestorPrincipal.guardararchivo(ruta_Archivo_save);
    }

    public void pintar() {
        //metodo para pintar la maquina
    }

    // </editor-fold>
    // <editor-fold desc="Atributos" defaultstate="collapsed" >
    private final Control_Simulator gestorPrincipal;

    private JMenuBar menuPrincipal;

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

    private final FileNameExtensionFilter filter
            = new FileNameExtensionFilter(
                    "Archivos .xml", "xml"
            );

    // </editor-fold>
}
