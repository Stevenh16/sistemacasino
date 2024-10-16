package org.bolivia.game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistemacasino.persistencia.Serializacion;
import sistemacasino.datos.Casino;
import sistemacasino.datos.Juego;
import sistemacasino.datos.Usuario;
import sistemacasino.exceptions.NegativeException;

public class DinoBettingGame extends Juego implements Serializable {
    Casino casino;
    Usuario usuario;
    private JPanel animationPanel;
    private JTextField betAmountField;
    private JLabel multiplierLabel;
    private JLabel potentialWinningsLabel;
    private JButton startBetButton;
    private JButton withdrawButton;
    private JButton startBatButton;

    private Timer gameTimer;
    private double multiplier;
    private double betAmount;
    private Random random;
    private int dinoX; // Posición X del dinosaurio
    private boolean movingRight; // Indica si el dinosaurio se está moviendo hacia la derecha

    public DinoBettingGame(String nombre,String descripcion, Usuario usuario, Casino casino) {
        super(nombre,descripcion);
        this.usuario=usuario;
        this.casino=casino;
        setTitle("Dino Juego");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initGeneratedComponents();
        random = new Random();
        dinoX = 80; // Posición inicial del dinosaurio
        movingRight = true; // El dinosaurio comienza moviéndose hacia la derecha
    }

    private void initGeneratedComponents() {
        // Initialize components
        animationPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.GREEN);
                g.fillRect(dinoX, 90, 100, 50); // Dinosaurio
                g.setColor(Color.BLACK);
                g.fillOval(dinoX + 30, 70, 20, 20); // Ojo
                g.fillOval(dinoX + 50, 70, 20, 20); // Ojo
                g.drawLine(dinoX + 20, 110, dinoX + 10, 130); // Mano izquierda
                g.drawLine(dinoX + 10, 130, dinoX + 30, 150); // Brazo izquierdo
                g.drawLine(dinoX + 80, 110, dinoX + 90, 130); // Mano derecha
                g.drawLine(dinoX + 90, 130, dinoX + 70, 150); // Brazo derecho
                g.setColor(Color.RED);
                g.fillOval(dinoX + 35, 130, 30, 20); // Boca
            }
        };

        betAmountField = new JTextField(10);
        multiplierLabel = new JLabel("Multiplicador: 1.0");
        potentialWinningsLabel = new JLabel("Ganancia Potencial: $0.00");
        startBetButton = new JButton("Iniciar apuesta");
        startBatButton = new JButton("Salir");
        withdrawButton = new JButton("Retirar");

        // Layout setup
        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Apuesta:"));
        controlPanel.add(betAmountField);
        controlPanel.add(startBetButton);
        controlPanel.add(startBatButton);
        controlPanel.add(withdrawButton);

        JPanel infoPanel = new JPanel();
        infoPanel.add(multiplierLabel);
        infoPanel.add(potentialWinningsLabel);

        getContentPane().add(animationPanel, BorderLayout.CENTER);
        getContentPane().add(controlPanel, BorderLayout.NORTH);
        getContentPane().add(infoPanel, BorderLayout.SOUTH);

        // Button actions
        startBetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startBet();
            }
        });
        
        startBatButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                setVisible(false);
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw();
            }
        });

        // Initially disable the withdraw button
        withdrawButton.setEnabled(false);
    }

    private void startBet() {
        try {
            betAmount = Double.parseDouble(betAmountField.getText());
            if(betAmount<=0 || betAmount > usuario.getBilletera().getSaldo()) throw new NegativeException("Monto negativo.");
            multiplier = 1.0;
            updateLabels();
            
            gameTimer = new Timer(100, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateGame();
                }
            });
            gameTimer.start();
            withdrawButton.setEnabled(true); // Enable the withdraw button
        } catch (NumberFormatException | NegativeException ex) {
            JOptionPane.showMessageDialog(this, "INGRESA UN VALOR VALIDO PARA LA APUESTA.");
        }
    }

    private void updateGame() {
        multiplier += 0.1;
        updateLabels();
        if(multiplier > 2.0) {
            gameTimer.setDelay(200); // Si el multiplicador es mayor que 5.0, reducir la velocidad
        }else {
        gameTimer.setDelay(100); // Si no, mantener la velocidad original
    }
        // Mover el dinosaurio
        if (movingRight) {
            dinoX += 10; // Mover 10 píxeles a la derecha
            if (dinoX >= getWidth() - 100) { // Si llega al borde derecho
                movingRight = false; // Cambiar dirección a la izquierda
            }
        } else {
            dinoX -= 5; // Mover 5 píxeles a la izquierda
            if (dinoX <= 0) { // Si llega al borde izquierdo
                movingRight = true; // Cambiar dirección a la derecha
            }
        }

        if (random.nextDouble() < 0.05) { // 5% de probabilidad de perder
            gameTimer.stop();
            withdrawButton.setEnabled(false); // Deshabilitar el botón de retirar
            JOptionPane.showMessageDialog(this, "Perdiste Intentalo Nuevamente."); // ¡Perdiste! Inténtalo de nuevo.
            casino.buscarUsuario(usuario.getCedula()).getBilletera().retirar((float) betAmount);
            try {
                new Serializacion().guardar(casino, "Casino.data");
            } catch (IOException ex) {
            }
            resetGame();
        }

        animationPanel.repaint(); // Repintar el panel de animación para actualizar la posición del dinosaurio
    }

    private void withdraw() {
        if (gameTimer != null) {
            gameTimer.stop();
            withdrawButton.setEnabled(false); // Deshabilitar el botón de retirar
            double winnings = betAmount * multiplier;
            winnings = Math.round(winnings * 100.0) / 100.0;
            casino.buscarUsuario(usuario.getCedula()).getBilletera().consignar((float) winnings);
            try {
                new Serializacion().guardar(casino, "Casino.data");
            } catch (IOException ex) {
            }
            JOptionPane.showMessageDialog(this, "Ganaste $" + winnings);
            resetGame();
        }
    }

    private void resetGame() {
        betAmount = 0.0;
        multiplier = 1.0;
        betAmountField.setText("");
        updateLabels();
        dinoX = 70; // Reiniciar la posición del dinosaurio
        movingRight = true; // El dinosaurio comienza moviéndose hacia la derecha
        animationPanel.repaint(); // Repintar el panel de animación para mostrar al dinosaurio en su posición inicial
    }

    private void updateLabels() {
        multiplierLabel.setText(String.format("Multiplicador: %.1f", multiplier));
        potentialWinningsLabel.setText(String.format("Ganancia Potencial: $%.2f", betAmount * multiplier));
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
