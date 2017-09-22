package simulador.modelo;

import java.util.ArrayList;

public class Nodo {

    public Nodo(int tipo, String name) {
        this.name = name;
        this.paths = new ArrayList<>();
        this.tipo = tipo;
    }
    public void addPaths(Path p) {
        paths.add(p);
    }

    public ArrayList<Path> getPathList() {
        return paths;
    }
    
    public String getname(){
        return name;
    }
    
    private final String name;
    private final int tipo;
    private final ArrayList<Path> paths;
}
