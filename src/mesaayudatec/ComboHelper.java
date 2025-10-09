package mesaayudatec;

import mesaayudatec.newpackage.model.MySqlConexion;
import javax.swing.*;
import java.sql.*;

public final class ComboHelper {

    private ComboHelper() {}

    // ---------- CARGAR COMBOS ----------
    public static void cargarEstados(JComboBox combo) {
        cargar(combo, "SELECT id, nombre FROM tb_estados ORDER BY id");
    }

    public static void cargarDepartamentos(JComboBox combo) {
        cargar(combo, "SELECT id, nombre FROM tb_departamento ORDER BY id");
    }

    public static void cargarPrioridades(JComboBox combo) {
        cargar(combo, "SELECT id, nombre FROM tb_prioridades ORDER BY valor");
    }

    // ✅ NUEVO MÉTODO: CARGAR USUARIOS (para el combo "Asignado")
    public static void cargarUsuarios(JComboBox combo) {
        combo.removeAllItems();
        String sql = "SELECT idusuario, nombre FROM tb_usuario ORDER BY nombre";
        try (Connection con = new MySqlConexion().getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                combo.addItem(new Item(rs.getInt("idusuario"), rs.getString("nombre")));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(combo,
                    "Error cargando usuarios: " + e.getMessage(),
                    "Error BD", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ---------- OBTENER ID ----------
    public static int getIdSeleccionado(JComboBox combo) {
        Object o = combo.getSelectedItem();
        if (o instanceof Item) {
            return ((Item) o).getId();
        }
        String s = o == null ? "" : o.toString();
        int k = s.indexOf(" - ");
        try {
            return (k >= 0) ? Integer.parseInt(s.substring(0, k).trim())
                            : Integer.parseInt(s.trim());
        } catch (Exception e) {
            return 0;
        }
    }

    // ---------- MÉTODO GENÉRICO ----------
    private static void cargar(JComboBox combo, String sql) {
        combo.removeAllItems();
        try (Connection con = new MySqlConexion().getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                combo.addItem(new Item(rs.getInt("id"), rs.getString("nombre")));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(combo,
                    "Error cargando combo: " + e.getMessage(),
                    "BD", JOptionPane.ERROR_MESSAGE);
        }
    }
}
