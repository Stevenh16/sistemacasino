package sistemacasino.datos;
import java.io.Serializable;
import java.util.ArrayList;
public class Arcade implements Serializable {
    private ArrayList<Juego> juegos;
    public Arcade() {
        this.juegos = new ArrayList<>();
    }
    public void agregarJuego(Juego juego){
        juegos.add(juego);
    }
    public String listarJuegos(){
        String lis="";
        for(Juego juego:juegos){
            lis+=juego.toString()+"\n";
        }
        return lis;
    }
    public Juego buscarJuego(int idjuego){
        for(Juego juego:juegos){
            if(juego.getIdjuego()==idjuego) return juego;
        }
        return null;
    }
    public boolean editarJuego(int id,Juego juego){
        Juego j=buscarJuego(id);
        if(j!=null){
            juegos.set(juegos.indexOf(j), juego);
            return true;
        }
        return false;
    }
    public boolean eliminarJuego(int idjuego){
        Juego juego=buscarJuego(idjuego);
        if(juego!=null) return juegos.remove(juego);
        return false;
    }
    public ArrayList<Juego> getJuegos() {
        return juegos;
    }
    public void setJuegos(ArrayList<Juego> juegos) {
        this.juegos = juegos;
    }
}
