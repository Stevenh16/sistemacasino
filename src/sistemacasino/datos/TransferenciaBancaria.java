package sistemacasino.datos;
import java.io.Serializable;
public class TransferenciaBancaria extends MetodoPago implements Serializable{
    private int codigoswift;
    private int codigoiban;
    public TransferenciaBancaria(int codigoswift, int codigoiban, String tipo) {
        super(tipo);
        this.codigoswift = codigoswift;
        this.codigoiban = codigoiban;
    }
    @Override
    public String toString() {
        return "TranferenciaBancaria{"+ super.toString() + "codigoswift=" + codigoswift + ", codigoiban=" + codigoiban + '}';
    }
    public int getCodigoswift() {
        return codigoswift;
    }
    public void setCodigoswift(int codigoswift) {
        this.codigoswift = codigoswift;
    }
    public int getCodigoiban() {
        return codigoiban;
    }
    public void setCodigoiban(int codigoiban) {
        this.codigoiban = codigoiban;
    }
}