package sistemacasino.datos;
import java.io.Serializable;
import java.time.LocalDate;
import sistemacasino.exceptions.DateException;
public class Partida implements Serializable{
    private Usuario usuario;
    private Juego juego;
    private LocalDate fecha;
    private int idpartida;
    private static int contador;
    public Partida(Usuario usuario, Juego juego, LocalDate fecha) throws DateException {
        if(fecha.isAfter(LocalDate.now())) throw new DateException("Fecha invalida.");
        this.usuario = usuario;
        this.juego = juego;
        this.fecha = fecha;
        this.idpartida = ++contador;
    }
    @Override
    public String toString() {
        return "Partida{" + "usuario=" + usuario.toString() + ", juego=" + juego.toString() + ", fecha=" + fecha + ", idpartida=" + idpartida + '}';
    }  
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Juego getJuego() {
        return juego;
    }
    public void setJuego(Juego juego) {
        this.juego = juego;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public int getIdpartida() {
        return idpartida;
    }
    public void setIdpartida(int idpartida) {
        this.idpartida = idpartida;
    }
}
