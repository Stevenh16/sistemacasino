package sistemacasino.datos;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sistemacasino.exceptions.DateException;
import sistemacasino.exceptions.NegativeException;
public class Casino implements Serializable{
    private String nombre;
    private Arcade arcade;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Transaccion> transacciones;
    private ArrayList<Partida> partidas;
    private ArrayList<Comentario> comentarios;
    private ArrayList<MetodoPago> metodospago; 
    // Expresión regular para validar un correo electrónico
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    public Casino(String nombre) {
        this.nombre = nombre;
        this.arcade= new Arcade();
        this.usuarios= new ArrayList<>();
        this.transacciones= new ArrayList<>();
        this.partidas= new ArrayList<>();
        this.comentarios= new ArrayList<>();
    }
    @Override
    public String toString() {
        return "Casino{" + "nombre=" + nombre + '}';
    }
    public void iniciarArcade(){
    }
    public void agregarUsuario(Usuario usuario){
        usuarios.add(usuario);
    }
    public boolean agregarTransaccion(int cedula, String tipometodo, String tipo, float cantidad) throws NegativeException{
        Usuario usuario = buscarUsuario(cedula);
        if(usuario!=null){
            MetodoPago metodo= usuario.buscarMetodo(tipometodo);
            if(metodo!=null){
                transacciones.add(new Transaccion(usuario,tipo,metodo,cantidad));
                return true;
            }
        }
        return false;
    }
    public boolean agregarPartida(int cedula, int idjuego) throws DateException{
        Usuario usuario=buscarUsuario(cedula);
        if(usuario!=null){
            Juego juego=arcade.buscarJuego(idjuego);
            if(juego!=null) {
                partidas.add(new Partida(usuario,juego,LocalDate.now()));
                return true;
            }
        }
        return false;
    }
    public boolean agregarComentario(int cedula, int idjuego, String comentario) throws DateException{
        Usuario usuario=buscarUsuario(cedula);
        if(usuario!=null){
            Juego juego=arcade.buscarJuego(idjuego);
            if(juego!=null) {
                comentarios.add(new Comentario(usuario,juego,comentario,LocalDate.now()));
                return true;
            }
        }
        return false;
    }
    public void agregarMetodo(MetodoPago metodo){
        metodospago.add(metodo);
    }
    public Usuario buscarUsuario(int cedula){
        for(Usuario usuario:usuarios){
            if(usuario.getCedula()==cedula) return usuario;
        }
        return null;
    }
    public Transaccion buscarTransaccion(int idtransaccion){
        for(Transaccion transaccion:transacciones){
            if(transaccion.getIdtransaccion()==idtransaccion) return transaccion;
        }
        return null;
    }
    public Comentario buscarComentario(int cedula, int idjuego){
        Usuario usuario=buscarUsuario(cedula);
        if(usuario!=null){
            Juego juego=arcade.buscarJuego(idjuego);
            if(juego!=null){
               for(Comentario comentario:comentarios){
                   if(comentario.getUsuario()==usuario && comentario.getJuego()==juego) return comentario;
               }
            }
        }
        return null;
    }
    public Partida buscarPartida(int cedula,int idjuego){
        Usuario usuario=buscarUsuario(cedula);
        if(usuario!=null){
            Juego juego=arcade.buscarJuego(idjuego);
            if(juego!=null){
                for(Partida partida:partidas){
                    if(partida.getUsuario()==usuario&&partida.getJuego()==juego) return partida;
                }
            }
        }
        return null;
    }
    public Partida buscarPartida(int idpartida){
        for(Partida partida:partidas){
            if(partida.getIdpartida()==idpartida) return partida;
        }
        return null;
    }
    public MetodoPago buscarMetodo(String tipo){
        for(MetodoPago metodo:metodospago){
            if(metodo.getTipo().equals(tipo)) return metodo;
        }
        return null;
    }
    public String listarUsuarios() throws NullPointerException{
        String lis="";
        for(Usuario usuario:usuarios){
            lis+=usuario.toString()+"\n";
        }
        return lis;
    }
    public String listarComentarios(){
        String lis="";
        for(Comentario comentario:comentarios){
            lis+=comentario.toString()+"\n";
        }
        return lis;
    }
    public String listarPartidas(){
        String lis="";
        for(Partida partida:partidas){
            lis+=partida.toString()+"\n";
        }
        return lis;
    }
    public String listarTransacciones(){
        String lis="";
        for(Transaccion transaccion:transacciones){
            lis+=transaccion.toString()+"\n";
        }
        return lis;
    }
    public String listarTransaccionesUsuario(int cedula){
        Usuario usuario=buscarUsuario(cedula);
        if(usuario!=null){
            String lis="";
            for(Transaccion transaccion:transacciones){
                if(transaccion.getUsuario()==usuario){
                    lis+=transaccion.toString()+"\n";
                }
            }
            return lis;
        }
        return "";
    }
    public String listarPartidasUsuario(int cedula){
        Usuario usuario=buscarUsuario(cedula);
        if(usuario!=null){
            String lis="";
            for(Partida partida:partidas){
                if(partida.getUsuario()==usuario){
                    lis+=partida.toString()+"\n";
                }
            }
            return lis;
        }
        return "";
    }
    public String listarPartidasJuego(int idjuego){
        Juego juego= arcade.buscarJuego(idjuego);
        if(juego!=null){
            String lis="";
            for(Partida partida:partidas){
                if(partida.getJuego()==juego){
                    lis+=partida.toString()+"\n";
                }
            }
            return lis;
        }
        return "";
    }
    public String listarComentariosUsuario(int cedula){
        Usuario usuario=buscarUsuario(cedula);
        if(usuario!=null){
            String lis="";
            for(Comentario comentario:comentarios){
                if(comentario.getUsuario()==usuario){
                    lis+=comentario.toString()+"\n";
                }
            }
            return lis;
        }
        return "";
    }
    public String listarComentariosJuego(int idjuego){
        Juego juego= arcade.buscarJuego(idjuego);
        if(juego!=null){
            String lis="";
            for(Comentario comentario:comentarios){
                if(comentario.getJuego()==juego){
                    lis+=comentario.toString()+"\n";
                }
            }
            return lis;
        }
        return "";
    }
    public String listarMetodos(){
        String lis="";
        for(MetodoPago metodo: metodospago){
            lis+=metodo.toString()+"\n";
        }
        return lis;
    }
    public String listarMetodosUsuario(int cedula){
        Usuario usuario = buscarUsuario(cedula);
        if(usuario!=null){
            return usuario.listarMetodos();
        }
        return "";
    }
    public boolean editarUsuario(int cedula, Usuario usuario){
        Usuario ueditar= buscarUsuario(cedula);
        if(ueditar!=null){
            usuarios.set(usuarios.indexOf(ueditar), usuario);
            return true;
        }
        return false;
    }
    public boolean editarMetodoPagoUsuario(int cedula, String tipo, MetodoPago metodo){
        Usuario usuario= buscarUsuario(cedula);
        if(usuario!=null){
            MetodoPago meditar=usuario.buscarMetodo(tipo);
            ArrayList<MetodoPago> metodos= usuario.getMetodospago();
            metodos.set(metodos.indexOf(meditar),metodo);
            usuario.setMetodospago(metodos);
            return true;
        }
        return true;
    }
    public boolean editarComentarioUsuario(int cedula, int idcomentario, int idjuego, Comentario comentario){
        Comentario ceditar= buscarComentario(cedula, idjuego);
        if(ceditar!=null){
            comentarios.set(comentarios.indexOf(ceditar),comentario);
            return true;
        }
        return false;
    }
    public boolean eliminarUsuario(int cedula){
        Usuario usuario=buscarUsuario(cedula);
        if(usuario!=null) return usuarios.remove(usuario);
        return false;
    }
    public boolean eliminarTransaccion(int idtransaccion){
        Transaccion transaccion=buscarTransaccion(idtransaccion);
        if(transaccion!=null) return transacciones.remove(transaccion);
        return false;
    }
    public boolean eliminarPartida(int idpartida){
        Partida partida=buscarPartida(idpartida);
        if(partida!=null) return partidas.remove(partida);
        return false;
    }
    public boolean eliminarComentario(int cedula, int idjuego){
        Comentario comentario=buscarComentario(cedula,idjuego);
        if(comentario!=null) return comentarios.remove(comentario);
        return false;
    }
    public boolean eliminarMetodoPago(String tipo){
         MetodoPago metodo= buscarMetodo(tipo);
         if(metodo!=null) return metodospago.remove(metodo);
         return false;
    }
    public boolean eliminarMetodoPagoUsuario(int cedula, String tipo){
        Usuario usuario=buscarUsuario(cedula);
        if(usuario!=null) return usuario.eliminarMetodo(tipo);
        return false;
    }
    public Usuario usuarioRegistrado(String correo,String contraseña){
        for(Usuario usuario:usuarios){
            if(usuario.getCorreo().equals(correo) && usuario.getContraseña().equals(contraseña)) return  usuario;
        }
        return null;
    }

    // Método para verificar si un String es un correo electrónico válido
    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Arcade getArcade() {
        return arcade;
    }
    public void setArcade(Arcade arcade) {
        this.arcade = arcade;
    }
    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }
    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    public ArrayList<Transaccion> getTransacciones() {
        return transacciones;
    }
    public void setTransacciones(ArrayList<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }
    public ArrayList<Partida> getPartidas() {
        return partidas;
    }
    public void setPartidas(ArrayList<Partida> partidas) {
        this.partidas = partidas;
    }
    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }
    public void setComentarios(ArrayList<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
    public ArrayList<MetodoPago> getMetodospago(){
        return metodospago;
    }
    public void setMetodospago(ArrayList<MetodoPago> metodospago){
        this.metodospago=metodospago;
    }
}