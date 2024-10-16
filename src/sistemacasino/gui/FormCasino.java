/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemacasino.gui;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sistemacasino.persistencia.Serializacion;
import sistemacasino.datos.Casino;
import sistemacasino.datos.Juego;
import sistemacasino.datos.Usuario;
import sistemacasino.exceptions.TamCedulaException;

/**
 *
 * @author Steven
 */
public class FormCasino {
    public static void main(String args[]) throws FileNotFoundException {Casino casino;
        try {
            casino = new Serializacion().recuperar("Casino.data");
            JOptionPane.showMessageDialog(null, casino.listarUsuarios());
            new FormIngreso(casino).setVisible(true);
        } catch (IOException | NullPointerException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}
