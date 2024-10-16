package sistemacasino.datos;
import java.io.Serializable;
public class MetodoPago implements Serializable{
    private String tipo;
    public MetodoPago(String tipo) {
        this.tipo = tipo;
    }
    @Override
    public String toString() {
        return "MetodoPago{" + "tipo=" + tipo + '}';
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
