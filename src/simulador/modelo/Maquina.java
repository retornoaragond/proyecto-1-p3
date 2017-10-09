/*
Esteban Espinoza Fallas 402290345
José Fabio Alfaro Quesada 207580494
*/

package simulador.modelo;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Maquina-Estados")
@XmlType(propOrder = {"maquina", "estinicio"})
public class Maquina {
    
    // <editor-fold desc="Constructor" defaultstate="collapsed">
    public Maquina() {
        maquina = new ArrayList<>();
        estinicio = false;
    }
    // </editor-fold>
    
    // <editor-fold desc="Metodos" defaultstate="collapsed">
    @XmlElementWrapper(name = "maquina")
    @XmlElement(name = "nodo")
    public ArrayList<Nodo> getmaquina() {
        return maquina;
    }

    public void setmaquina(ArrayList<Nodo> maquina) {
        this.maquina= maquina;
    }

    @XmlElement(name = "inicial")
    public boolean getEstinicio() {
        return estinicio;
    }

    public void setEstinicio(boolean e) {
        estinicio = e;
    }

    public void limpiar() {
        System.out.println("Eliminando la maquina");
        maquina.removeAll(maquina);
        estinicio = false;
    }

    public void agregar_estado(int tipo, String nom) {
        Nodo n = new Nodo(tipo, nom);
        if (!estinicio) {
            if (tipo == 1) {
                estinicio = true;
                Nodo apuntador = n;
            }
            maquina.add(n);
            System.out.println("Se agrego un estado a la maquina");
        } else {
            if (tipo == 1) {
                JOptionPane.showMessageDialog(null,
                        "No puede haber mas de un estado inicial.");
            } else {
                maquina.add(n);
                System.out.println("Se agrego un estado a la maquina");
            }
        }
    }

    public void agregar_path(Nodo n, String tag, Nodo next) {
        Path transicion = new Path(tag, searchNext(next));
        n.addPaths(transicion);
    }
    // </editor-fold>
    
    // <editor-fold desc="Verificar" defaultstate="collapsed">
    public boolean verificar(String hilera) {
        if (estinicio) {
            return verificadorR(hilera.toCharArray(), 0, maquina.get(0));
        } else {
            JOptionPane.showMessageDialog(null,
                    "Debe existir un estado inicial almenos.");
            return false;
        }
    }

    public int searchNext(Nodo n) {
        int i;
        for (i = 0; i < maquina.size(); i++) {
            if (maquina.get(i).equals(n)) {
                break;
            }
        }
        return i;
    }

    private boolean verificadorR(char[] s, int h, Nodo a) {
        if (h == s.length) {
            return accept(a);
        } else {
            boolean flag = false;
            boolean enter = false;
            boolean exist = false;
            for (int i = 0; i < a.getpaths().size(); i++) {
                if (a.getpaths().get(i).isInTag(s[h])) {
                    enter = true;
                    exist = true;
                    flag = verificadorR(s, h + 1, maquina.get(a.getpaths().get(i).getdestiny()));
                }
                if (flag) {
                    break;
                }
            }
            if(!exist){
                return false;
            }
            if (enter == true) {
                return flag;
            }
            return accept(a);
        }
    }

    private boolean accept(Nodo n) {
        return n.gettipo() == 3;
    }
    // </editor-fold>
    
    // <editor-fold desc="Atributos" defaultstate="collapsed">
    private ArrayList<Nodo> maquina;
    private boolean estinicio;
    // </editor-fold>
}
