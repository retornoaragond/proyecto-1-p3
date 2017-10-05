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

    public void agregar_path(Nodo n, String tag, Nodo next) {
        Path transicion = new Path(tag, searchNext(next));
        n.addPaths(transicion);
    }

    public boolean verificar(String hilera) {

        return verificadorR(hilera.toCharArray(), 0, maquina.get(0));

    }
    
    public int searchNext(Nodo n){
        int i;
        for( i = 0; i < maquina.size(); i++){
            if(maquina.get(i).equals(n)){
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
            for (int i = 0; i < a.getPathList().size(); i++) {

                if (a.getPathList().get(i).isInTag(s[h])) {
                    enter = true;
                    flag = verificadorR(s, h + 1, maquina.get(a.getPathList().get(i).getDestiny()));
                }
                if (flag) {
                    break;
                }
            }
            if (enter == true) {
                return flag;
            }
            return accept(a);
        }
    }

    private boolean accept(Nodo n) {
        return n.getTipo() == 3;
    }

    private ArrayList<Nodo> maquina;
    private final ArrayList<Nodo> apuntadores;

}

