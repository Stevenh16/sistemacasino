/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemacasino.exceptions;

/**
 *
 * @author Steven
 */
public class NotFoundMetodoExcetion extends Exception {

    public NotFoundMetodoExcetion(String no_cuenta_con_ese_metodo_de_pago) {
        super(no_cuenta_con_ese_metodo_de_pago);
    }
    
}
