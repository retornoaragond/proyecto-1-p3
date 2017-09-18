
package simulador.modelo;
import java.util.ArrayList;
import java.util.List;

public class Nodo {
    
    public Nodo(ArrayList<String> a, boolean b){
        this.tags = a;
        this.paths = new ArrayList<>();
        accept = b;  
    }
    
    public Nodo(ArrayList<String> a){
        this(a, false);
    }
    
    public Nodo(){
        this(new ArrayList<>(), false);
    }
    
    public boolean isAccept(){
        return accept;
    }
    
    public void setPaths(String s, Path p){
        tags.add(s);
        paths.add(p);
    }
    
    public int getTag(String s){
        for(int i = 0; i < tags.size(); i++){
            if(tags.get(i) == null ? s == null : tags.get(i).equals(s)){
                return i;
            }
        }
        return -1;
    }
    
    
    private final boolean accept;
    private final List<String> tags;
    private final List<Path> paths;
}