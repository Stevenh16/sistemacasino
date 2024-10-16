package sistemacasino.datos;
import sistemacasino.exceptions.TamCedulaException;
import java.io.Serializable;
import java.util.ArrayList;
public class Usuario implements Serializable{
    private int cedula;
    private String nombre;
    private String correo;
    private String contraseña;
    private String tipo;
    private Billetera billetera;
    private ArrayList<MetodoPago> metodospago;
    public Usuario(){
    }
    public Usuario(int cedula, String nombre, String correo, String contraseña, String tipo) throws TamCedulaException{
        if(cedula/1000000000<1) throw new TamCedulaException("Cedula invalida");
        this.cedula = cedula;
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
        this.tipo = tipo;
        this.billetera=new Billetera();
        this.metodospago= new ArrayList<>();
    }
    @Override
    public String toString() {
        return "Usuario{" + "cedula=" + cedula + ", nombre=" + nombre + ", correo=" + correo + ", contrase\u00f1a=" + contraseña + ", tipo=" + tipo + ", billetera=" + billetera.toString() + '}';
    }
    public void agregarMetodo(MetodoPago metodo){
        metodospago.add(metodo);
    }
    public MetodoPago buscarMetodo(String tipo){
        for(MetodoPago metodo:metodospago){
            if(metodo.getTipo().equals(tipo)) return metodo;
        }
        return null;
    }
    public String listarMetodos(){
        String lis="";
        for(MetodoPago metodo: metodospago){
            lis+=metodo.toString()+"\n";
        }
        return lis;
    }
    public boolean eliminarMetodo(String tipo){
        MetodoPago metodo=buscarMetodo(tipo);
        if(metodo!=null) return metodospago.remove(metodo);
        return false;
    }
    public int getCedula() {
        return cedula;
    }
    public void setCedula(int cedula) {
        this.cedula = cedula;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getContraseña() {
        return contraseña;
    }
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public Billetera getBilletera() {
        return billetera;
    }
    public void setBilletera(Billetera billetera) {
        this.billetera = billetera;
    }
    public ArrayList<MetodoPago> getMetodospago() {
        return metodospago;
    }
    public void setMetodospago(ArrayList<MetodoPago> metodospago) {
        this.metodospago = metodospago;
    }
}