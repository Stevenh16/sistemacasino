package sistemacasino.persistencia;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import sistemacasino.datos.Casino;
public class Serializacion {
    public void guardar(Casino c, String archivo) throws FileNotFoundException, IOException{
        ObjectOutputStream gua;
        try (FileOutputStream file = new FileOutputStream(archivo)) {
            gua = new ObjectOutputStream(file);
            gua.writeObject(c);
        }
        gua.close();
    }
    public Casino recuperar(String archivo) throws FileNotFoundException, IOException, ClassNotFoundException{
        ObjectInputStream rec;
        Casino c;
        try (FileInputStream file = new FileInputStream(archivo)) {
            rec = new ObjectInputStream(file);
            c = (Casino) rec.readObject();
        }
        rec.close();
        return c;
        
    }
}
