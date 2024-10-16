package org.bolivia.game;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
/**
 * @web http://www.jc-mouse.net
 * @author Mouse
 */
public class JFigura extends JComponent implements Serializable{
    
    private ImageIcon image= new ImageIcon( getClass().getResource("/org/bolivia/game/res/figuras.jpg"));           
    private int y=0;
    private Dimension d = new Dimension(100,100);
    
    /** Constructor de clase */
    public JFigura()
    {
        setSize(d);
        setPreferredSize(d);
        setVisible(true);
    }
    
    /** Actualiza coordenada en Y */
    public void updateY(int value)
    {
        y = value;
        repaint();
    }
    
    @Override
    public void paint (Graphics g){
        super.paint(g);
        g.drawImage(image.getImage(), 0, y, 100, 400, this);                
	g.dispose();
    }
 
}
