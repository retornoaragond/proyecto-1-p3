package simulador.modelo;

import java.util.ArrayList;
import java.util.List;

public class Maquina {

    public Maquina() {
        maquina = new ArrayList<>();
        apuntadores = new ArrayList<>();
    }

    public List<Nodo> get_maquina() {
        return maquina;
    }

    public List<Nodo> get_apuntadores() {
        return apuntadores;
    }

    public void set_maquina(ArrayList<Nodo> maquina) {
        this.maquina = maquina;
    }

    public void limpiar() {
        System.out.println("Eliminando la maquina");
        maquina.removeAll(maquina);
        apuntadores.removeAll(apuntadores);
    }

    public void agregar_estado(int tipo, String nom) {
        Nodo n = new Nodo(tipo, nom);
        if (tipo == 1) {
            Nodo apuntador = n;
            apuntadores.add(apuntador);
        }
        maquina.add(n);
        System.out.println("Se agrego un estado a la maquina");
    }
    
    public void agregar_path(Nodo n,String tag,Nodo next){
        Path transicion = new Path(tag, next);
        n.addPaths(transicion);
    }
    
    public void verificar(String hilera){
        //validar las entradas concidan con los paths
        //debe de generear la ventana de aceptacion
    }

    private ArrayList<Nodo> maquina;
    private final ArrayList<Nodo> apuntadores;

}
