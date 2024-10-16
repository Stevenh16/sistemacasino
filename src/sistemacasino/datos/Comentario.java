package sistemacasino.datos;
import java.io.Serializable;
import java.time.LocalDate;
import sistemacasino.exceptions.DateException;
public class Comentario implements Serializable{
    private Usuario usuario;
    private Juego juego;
    private String comentario;
    private LocalDate fecha;
    public Comentario(Usuario usuario, Juego juego, String comentario, LocalDate fecha) throws DateException {
        if(fecha.isAfter(LocalDate.now())) throw new DateException("Fecha invalida.");
        this.usuario = usuario;
        this.juego = juego;
        this.comentario = comentario;
        this.fecha=fecha;
    }
    @Override
    public String toString() {
        return "Comentario{" + "usuario=" + usuario.toString() + ", juego=" + juego.toString() + ", comentario=" + comentario + '}';
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
    public String getComentario() {
        return comentario;
    }
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    public void setFecha(LocalDate fecha){
        this.fecha=fecha;
    }
    public LocalDate getFecha(){
        return fecha;
    }
}
