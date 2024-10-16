package sistemacasino.datos;

import java.io.Serializable;

public class Premio implements Serializable {
    private String nombre;
    private String descripcion;
    public Premio(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    @Override
    public String toString() {
        return "Premio{" + "nombre=" + nombre + ", descripcion=" + descripcion + '}';
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
