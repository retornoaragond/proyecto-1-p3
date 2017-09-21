
package simulador.modelo;

import java.util.LinkedList;
import java.util.List;


public class Nodo {
    
    public Nodo(int b, String s){  
        name = s;
        this.paths = new LinkedList<>();
        tipo = b;
    }
    
//    public Nodo(String s){
//        this(0, s);
//    }
    
    public int isAccept(){
        return tipo;
    }
    
    public void setPaths(Path p){
        paths.add(p);
    }
    
    public List<Path> getPathList(){
        return paths;
    }
    
    private final String name;
    private final int tipo;
    private final List<Path> paths;
}