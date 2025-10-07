package mesaayudatec;

import dao.TicketDAO;
import javax.swing.*;
import java.util.UUID;

public class RegistroIncidencia extends JFrame {

    private JTextField txtTitulo;
    private JTextArea txtDescripcion;
    private JComboBox<String> cbEstado, cbDepartamento, cbPrioridad;
    private JButton btnGuardar;

    private final int idUsuario;  // lo recibimos desde el login

    public RegistroIncidencia(String nombreUsuario, int idUsuario) {
        this.idUsuario = idUsuario;

        setTitle("Registro de Incidencias - Usuario: " + nombreUsuario);
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Campo título
        panel.add(new JLabel("Título:"));
        txtTitulo = new JTextField();
        panel.add(txtTitulo);

        // Campo descripción
        panel.add(new JLabel("Descripción:"));
        txtDescripcion = new JTextArea(5, 30);
        panel.add(new JScrollPane(txtDescripcion));

        // Combo Estado
        panel.add(new JLabel("Estado:"));
        cbEstado = new JComboBox<>();
        panel.add(cbEstado);

        // Combo Departamento
        panel.add(new JLabel("Departamento:"));
        cbDepartamento = new JComboBox<>();
        panel.add(cbDepartamento);

        // Combo Prioridad
        panel.add(new JLabel("Prioridad:"));
        cbPrioridad = new JComboBox<>();
        panel.add(cbPrioridad);

        // Botón Guardar
        btnGuardar = new JButton("Grabar");
        btnGuardar.addActionListener(e -> guardarIncidencia());
        panel.add(btnGuardar);

        add(panel);

        // Cargar datos de combos
        cargarCombos();
    }

    private void cargarCombos() {
        TicketDAO dao = new TicketDAO();
        dao.getEstados().forEach(cbEstado::addItem);
        dao.getDepartamentos().forEach(cbDepartamento::addItem);
        dao.getPrioridades().forEach(cbPrioridad::addItem);
    }

    private void guardarIncidencia() {
        String titulo = txtTitulo.getText().trim();
        String descripcion = txtDescripcion.getText().trim();
        String estado = (String) cbEstado.getSelectedItem();
        String departamento = (String) cbDepartamento.getSelectedItem();
        String prioridad = (String) cbPrioridad.getSelectedItem();

        if (titulo.isEmpty() || descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa todos los campos.");
            return;
        }

        // Generar folio único
        String folio = UUID.randomUUID().toString().substring(0, 8);

        // Aquí deberías mapear estado/departamento/prioridad → id numérico real
        // Por simplicidad, supongamos que son 1
        int idEstado = 1;
        int idDepartamento = 1;
        int idPrioridad = 1;

        TicketDAO dao = new TicketDAO();
        boolean ok = dao.registrarTicket(folio, titulo, descripcion,
                idDepartamento, idUsuario, idEstado, idPrioridad);

        if (ok) {
            JOptionPane.showMessageDialog(this, "✅ Ticket registrado correctamente.");
            txtTitulo.setText("");
            txtDescripcion.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "❌ Error al guardar ticket.");
        }
    }
}
