
package simulador.modelo;

public class Path {
    
    public Path(String s, Nodo d){
        this.tag = s;
        this.destiny = d;
    }
    
    public Path(String s){
        this(s, null);
    }

    public void setDestiny(Nodo n){
        this.destiny = n;
    }
    
    public String getTag(){
        return tag;
    }
    
    public Nodo getNodo(){
        return destiny;
    }
    
    private String tag;
    private Nodo destiny;
}