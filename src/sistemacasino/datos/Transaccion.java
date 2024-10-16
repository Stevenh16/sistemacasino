package sistemacasino.datos;
import sistemacasino.exceptions.NegativeException;
import java.io.Serializable;
public class Transaccion implements Serializable{
    private int idtransaccion;
    private static int contador;
    private Usuario usuario;
    private String tipo;
    private MetodoPago metodopago;
    private float cantidad;
    public Transaccion(Usuario usuario, String tipo, MetodoPago metodopago, float cantidad) throws NegativeException {
        if(cantidad<0) throw new NegativeException("No puede haber una cantidad negativa.");
        this.usuario = usuario;
        this.tipo = tipo;
        this.metodopago = metodopago;
        this.cantidad = cantidad; 
        this.idtransaccion= ++contador;
    }
    @Override
    public String toString() {
        return "Transaccion{" + "idtransaccion=" + idtransaccion + ", usuario=" + usuario.toString() + ", tipo=" + tipo + ", metodopago=" + metodopago.toString() + ", cantidad=" + cantidad + '}';
    }
    public int getIdtransaccion() {
        return idtransaccion;
    }
    public void setIdtransaccion(int idtransaccion) {
        this.idtransaccion = idtransaccion;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public MetodoPago getMetodopago() {
        return metodopago;
    }
    public void setMetodopago(MetodoPago metodopago) {
        this.metodopago = metodopago;
    }
    public float getCantidad() {
        return cantidad;
    }
    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }
}
