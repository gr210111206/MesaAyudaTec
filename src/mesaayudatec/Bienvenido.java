package mesaayudatec;

import javax.swing.*;

public class Bienvenido extends JFrame {
    public Bienvenido(String nombre) {
        setTitle("Mesa de Ayuda - Bienvenido");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 320);
        setLocationRelativeTo(null);

        JLabel lbl = new JLabel("Bienvenido, " + nombre, SwingConstants.CENTER);
        lbl.setFont(lbl.getFont().deriveFont(24f));
        add(lbl);
    }
}
