/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemacasino.exceptions;

/**
 *
 * @author Steven
 */
public class CantidadException extends Exception {

    public CantidadException(String saldo_insuficiente) {
        super(saldo_insuficiente);
    }
    
}
