
package simulador.modelo;

import java.util.LinkedList;
import java.util.List;


public class Nodo {
    
    public Nodo(boolean b, String s){
        
        name = s;
        this.paths = new LinkedList<>();
        accept = b;
        
    }
    
    public Nodo(String s){
        this(false, s);
    }
    
    
    public boolean isAccept(){
        return accept;
    }
    
    public void setPaths(Path p){
        paths.add(p);
    }
    
    public List<Path> getPathList(){
        return paths;
    }
    
    
    private final String name;
    private final boolean accept;
    private final List<Path> paths;
}