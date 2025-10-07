package mesaayudatec;

public class MesaAyudaTec {
    public static void main(String[] args) {
        // Inicia directamente con la ventana de Login
        java.awt.EventQueue.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }
}
