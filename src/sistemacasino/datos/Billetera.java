package sistemacasino.datos;

import java.io.Serializable;

public class Billetera implements Serializable{
    private float saldo;
    public Billetera() {
    }
    @Override
    public String toString() {
        return "Billetera{" + "saldo=" + saldo + '}';
    }
    public void retirar(float cantidad){
        saldo-=cantidad;
    }
    public void consignar(float cantidad){
        saldo+=cantidad;
    }
    public float getSaldo() {
        return saldo;
    }
    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
}
