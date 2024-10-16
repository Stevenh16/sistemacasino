package sistemacasino.datos;
import sistemacasino.exceptions.DateException;
import java.io.Serializable;
import java.time.LocalDate;
public class Tarjeta extends MetodoPago implements Serializable {
    private LocalDate fechavencimiento;
    private int codigoseguridad;
    private String tipotarjeta;
    public Tarjeta(LocalDate fechavencimiento, int codigoseguridad, String tipotarjeta,String tipo) throws DateException {
        super(tipo);
        if(fechavencimiento.isBefore(LocalDate.now())) throw new DateException("Fecha invalida.");
        this.fechavencimiento = fechavencimiento;
        this.codigoseguridad = codigoseguridad;
        this.tipotarjeta = tipotarjeta;
    }
    @Override
    public String toString() {
        return "TarjetaCredito{"+ super.toString() + "fechavencimiento=" + fechavencimiento + ", codigoseguridad=" + codigoseguridad + ", tipotarjeta=" + tipotarjeta + '}';
    }
    public LocalDate getFechavencimiento() {
        return fechavencimiento;
    }
    public void setFechavencimiento(LocalDate fechavencimiento) {
        this.fechavencimiento = fechavencimiento;
    }
    public int getCodigoseguridad() {
        return codigoseguridad;
    }
    public void setCodigoseguridad(int codigoseguridad) {
        this.codigoseguridad = codigoseguridad;
    }
    public String getTipotarjeta() {
        return tipotarjeta;
    }
    public void setTipotarjeta(String tipotarjeta) {
        this.tipotarjeta = tipotarjeta;
    }
}
