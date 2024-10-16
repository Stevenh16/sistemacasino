package org.bolivia.game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistemacasino.persistencia.Serializacion;
import sistemacasino.datos.Casino;
import sistemacasino.datos.Juego;
import sistemacasino.datos.Usuario;
import sistemacasino.exceptions.NegativeException;

public class RuletaPanel extends Juego {
    Usuario usuario;
    Casino casino;
    // Atributos para la ruleta
    private double angulo;
    private Timer timer;
    private int numRanuras = 20;
    private Color resultadoColor = Color.WHITE;

    // Atributos para las apuestas
    private JTextField montoApuesta;
    private JComboBox<ColorWrapper> colorApuesta;
    private JLabel resultadoMensaje;

    // Clase envolvente para Color
    private class ColorWrapper {
        private Color color;
        private String nombre;

        public ColorWrapper(Color color, String nombre) {
            this.color = color;
            this.nombre = nombre;
        }

        public Color getColor() {
            return color;
        }

        @Override
        public String toString() {
            return nombre;
        }
    }

    public RuletaPanel(String nombre,String descripcion, Usuario usuario, Casino casino) {
        super(nombre,descripcion);
        this.usuario=usuario;
        this.casino=casino;
        angulo = 0;
        setTitle("Ruleta");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        getContentPane().setBackground(new Color(0, 128, 0)); // Color verde de casino
        setLayout(new BorderLayout());

        // Crear componentes para las apuestas con diseño de arcade
        Font arcadeFont = new Font("Press Start 2P", Font.BOLD, 12);
        LineBorder arcadeBorder = new LineBorder(Color.YELLOW, 2);

        montoApuesta = new JTextField(10);
        montoApuesta.setFont(arcadeFont);
        montoApuesta.setBackground(Color.BLACK);
        montoApuesta.setForeground(Color.GREEN);
        montoApuesta.setBorder(arcadeBorder);

        colorApuesta = new JComboBox<>(new ColorWrapper[]{
            new ColorWrapper(Color.RED, "Rojo"),
            new ColorWrapper(Color.BLACK, "Negro")
        });
        colorApuesta.setFont(arcadeFont);
        colorApuesta.setBackground(Color.BLACK);
        colorApuesta.setForeground(Color.GREEN);
        colorApuesta.setBorder(arcadeBorder);

        resultadoMensaje = new JLabel("");
        resultadoMensaje.setFont(arcadeFont);
        resultadoMensaje.setForeground(Color.WHITE);

        // Crear botón "Girar" con diseño arcade y agregar acción
        JButton botonGirar = new JButton("Girar");
        botonGirar.setPreferredSize(new Dimension(200, 50)); // Tamaño grande
        botonGirar.setFont(new Font("Press Start 2P", Font.BOLD, 20)); // Fuente grande y en negrita
        botonGirar.setBackground(new Color(0, 100, 0)); // Color de fondo verde oscuro
        botonGirar.setForeground(Color.WHITE); // Texto blanco
        botonGirar.setFocusPainted(false); // Sin borde de enfoque
        botonGirar.setBorder(arcadeBorder);

        botonGirar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int monto=Integer.parseInt(montoApuesta.getText());
                    if(monto<=0 || monto>usuario.getBilletera().getSaldo()) throw new NegativeException("Monto invalido");
                    girar();
                }catch(NumberFormatException | NegativeException ex){
                    JOptionPane.showMessageDialog(null, "Ingrese un monto valido");
                }
            }
        });

        // Crear panel para las apuestas y agregarlo al panel principal
        JPanel panelApuestas = new JPanel(new GridLayout(0, 1, 5, 5));
        panelApuestas.setBackground(Color.BLACK); // Fondo negro estilo arcade
        panelApuestas.setBorder(BorderFactory.createCompoundBorder(
            arcadeBorder, BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        panelApuestas.add(crearEtiqueta("Monto a apostar: ", arcadeFont));
        panelApuestas.add(montoApuesta);
        panelApuestas.add(crearEtiqueta("Color: ", arcadeFont));
        panelApuestas.add(colorApuesta);
        panelApuestas.add(crearEtiqueta("Resultado: ", arcadeFont));
        panelApuestas.add(resultadoMensaje);
        panelApuestas.add(botonGirar); // Agregar botón "Girar" al panel de apuestas

        // Configurar el tamaño del panel de apuestas para ocupar el 40% del ancho
        JPanel containerApuestas = new JPanel(new BorderLayout());
        containerApuestas.add(panelApuestas, BorderLayout.NORTH);
        containerApuestas.setPreferredSize(new Dimension(400, getHeight()));
        containerApuestas.setBackground(Color.BLACK); // Fondo negro estilo arcade

        add(containerApuestas, BorderLayout.EAST);

        // Agregar un JPanel para dibujar la ruleta
        JPanel panelRuleta = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                // Calcular el tamaño y la posición de la ruleta
                int panelWidth = getWidth();
                int panelHeight = getHeight();
                int ruletaSize = Math.min((int) (panelWidth * 0.8), panelHeight);
                int x = (panelWidth - ruletaSize) / 2;
                int y = (panelHeight - ruletaSize) / 2;
                int radio = ruletaSize / 2;
                int separacion = 360 / numRanuras;

                // Dibujar la ruleta
                for (int i = 0; i < numRanuras; i++) {
                    if (i % 2 == 0) {
                        g2d.setColor(Color.RED);
                    } else {
                        g2d.setColor(Color.BLACK);
                    }
                    g2d.fillArc(x, y, ruletaSize, ruletaSize, (int) angulo + i * separacion, separacion);
                }

                // Dibujar el puntero estático
                g2d.setColor(Color.BLACK);
                g2d.fillPolygon(new int[]{x + radio - 5, x + radio + 5, x + radio}, new int[]{y - 10, y - 10, y}, 3);

                // Dibujar borde
                g2d.setColor(Color.BLACK);
                g2d.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
            }
        };
        panelRuleta.setBackground(new Color(0, 128, 0));
        add(panelRuleta, BorderLayout.CENTER);
    }

    private JLabel crearEtiqueta(String texto, Font font) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setFont(font);
        etiqueta.setForeground(Color.WHITE);
        return etiqueta;
    }

    public void girar() {
        Random random = new Random();
        int randomInt = 100 + random.nextInt(101);

        if (timer == null || !timer.isRunning()) {
            timer = new Timer(10, new ActionListener() {
                private int steps = 0;
                private final int totalSteps = randomInt;
                private final double anguloInicial = angulo;

                @Override
                public void actionPerformed(ActionEvent e) {
                    angulo += 5;
                    steps++;
                    repaint();

                    if (steps >= totalSteps) {
                        ((Timer) e.getSource()).stop();

                        // Ajustar el ángulo final para que caiga en una ranura
                        double anguloFinal = angulo % 360;
                        int ranuraIndex = (int) (anguloFinal / (360.0 / numRanuras));
                        resultadoColor = (ranuraIndex % 2 == 0) ? Color.RED : Color.BLACK;

                        // Verificar si el jugador gana o pierde
                        boolean gano = false;
                        String mensaje;
                        ColorWrapper colorSeleccionado = (ColorWrapper) colorApuesta.getSelectedItem();
                        if (resultadoColor.equals(colorSeleccionado.getColor())) {
                            gano = true;
                            float monto = Float.parseFloat(montoApuesta.getText());
                            casino.buscarUsuario(usuario.getCedula()).getBilletera().consignar(monto);
                            try {
                                new Serializacion().guardar(casino, "Casino.data");
                            } catch (IOException ex) {
                            }
                            mensaje = "FELICIDADES HAS GANADO: $"+montoApuesta.getText();
                        } else {
                            float monto = Float.parseFloat(montoApuesta.getText());
                            casino.buscarUsuario(usuario.getCedula()).getBilletera().retirar(monto);
                            try {
                                new Serializacion().guardar(casino, "Casino.data");
                            } catch (IOException ex) {
                            }
                            mensaje = "PERDISTE, MEJOR SUERTE A LA PROXIMA";
                        }
                        try {
                            new Serializacion().guardar(casino,"Casino.data");
                        } catch (IOException ex) {
                        }
                        // Actualizar el mensaje de resultado
                        resultadoMensaje.setText(mensaje);
                        if (gano) {
                            resultadoMensaje.setForeground(Color.GREEN);
                        } else {
                            resultadoMensaje.setForeground(Color.RED);
                        }
                    }
                }
            });
            timer.start();
        }
    }
    public Color getResultadoColor() {
        return resultadoColor;
    }
}
