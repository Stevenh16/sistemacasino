package org.bolivia.game;
import java.awt.Color;
import java.io.Serializable;
import java.util.concurrent.ExecutionException;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
/**
 * @web http://www.jc-mouse.net/
 * @author Mouse
 */
public class CheckWorker extends SwingWorker<Boolean, Void> implements Serializable{
    
    private JLabel label;
    private JTextField credito;
    private JTextField apuesta;
    private JLabel boton;
    SwingWorker swingWorkerA;
    SwingWorker swingWorkerB;
    SwingWorker swingWorkerC;
    
    /**
     * Constructor de clase
     */
    CheckWorker(SwingWorker a, SwingWorker b, SwingWorker c, JLabel lb,JTextField cr, JTextField ap, JLabel btn)
    {
        this.swingWorkerA=a;
        this.swingWorkerB=b;
        this.swingWorkerC=c;
        label = lb;
        credito = cr;
        apuesta = ap;
        boton = btn;
    }
    
    @Override
    protected Boolean doInBackground() throws Exception {
        
        int val1= (Integer) swingWorkerA.get(); 
        int val2= (Integer) swingWorkerB.get(); 
        int val3= (Integer) swingWorkerC.get();         
        if( val1==val2 && val2 == val3 )
        {            
            return true;//gana
        }        
        else
        {            
            return false;//pierde
        }        
    }
    
    @Override
    protected void done(){   
        try {
            //segun resultado de juego actualiza interfaz
            if( get() )
            {
                float ncredito=Float.parseFloat(credito.getText()) + Float.parseFloat(apuesta.getText());
                credito.setText( ""+ncredito);
                label.setForeground(Color.yellow);
                label.setText( "GANASTE!" );                
            }
            else
            {
                float ncredito=Float.parseFloat(credito.getText()) - Float.parseFloat(apuesta.getText());
                credito.setText( ""+ ncredito);
                label.setForeground(Color.red);
                label.setText( "PERDISTE!" );                
            }           
            boton.setEnabled(true);
        } catch (InterruptedException ex) {
            System.err.println("InterruptedException: " + ex.getMessage());
        } catch (ExecutionException ex) {
            System.err.println("ExecutionException: " + ex.getMessage());
        }
    }
    
}
