package sistemacasino.datos;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JFrame;
public class Juego extends JFrame implements Serializable {
    private int idjuego;
    private static int contador;
    private String nombre;
    private String descripción;
    private ArrayList<Premio> premios;
    public Juego(String nombre, String descripción) {
        this.nombre = nombre;
        this.descripción = descripción;
        this.idjuego=++contador;
        this.premios= new ArrayList<>();
    }
    @Override
    public String toString() {
        return "Juego{" + "idjuego=" + idjuego + ", nombre=" + nombre + ", descripci\u00f3n=" + descripción + '}';
    }
    public void agregarPremio(Premio premio){
        premios.add(premio);
    }
    public Premio buscarPremio(String nombre){
        for(Premio premio:premios){
            if(premio.getNombre().equals(nombre)) return premio;
        }
        return null;
    }
    public String listarPremios(){
        String lis = "";
        for(Premio premio:premios){
            lis+=premio.toString()+"\n";
        }
        return lis;
    }
    public boolean eliminarPremio(String nombre){
        Premio premio=buscarPremio(nombre);
        if(premio!=null) return premios.remove(premio);
        return false;
    }
    public int getIdjuego() {
        return idjuego;
    }
    public void setIdjuego(int idjuego) {
        this.idjuego = idjuego;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripción() {
        return descripción;
    }
    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }
    public ArrayList<Premio> getPremios() {
        return premios;
    }
    public void setPremios(ArrayList<Premio> premios) {
        this.premios = premios;
    }
}
