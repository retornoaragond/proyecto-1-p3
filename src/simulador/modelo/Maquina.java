
package simulador.modelo;


public class Maquina {
    
    public Maquina(Nodo r, boolean n){
        root = r;
        ND = n;
    }
    
    public Maquina(){
        this(null, false);
    }
    
    public Nodo getRoot(){
        
        return root;
    }
    
    public void setRoot(Nodo r){
        root = r;
    }
    
    public void setND(boolean b){
        ND = b;
    }
    
//    public boolean isAcceptD(String s) {
//        return searchAcceptD(s, root);
//    }
    
//    private boolean searchAcceptD(String s, Nodo n){
//        if(" ".equals(s)){
//            return n.isAccept();
//        }else{
//            
//            return true;
//        }
//    }
    
    public boolean isND(){
        return ND;
    }
    
    //To do
//    public boolean isAcceptND(String s) {
//        return searchAcceptND(s, root);
//    }
//    private boolean searchAcceptND(String s, Nodo n){
//        if(" ".equals(s)){
//            return n.isAccept();
//        }else{
//            
//            return true;
//        }
//    }
    
    
    private boolean ND;
    private Nodo root;

    

    
}
