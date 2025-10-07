package dao;

import mesaayudatec.newpackage.model.MySqlConexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {

    // Registrar ticket en la BD
    public boolean registrarTicket(String folio, String titulo, String descripcion,
                                   int idDepartamento, int idUsuario, int idEstado, int idPrioridad) {
        String sql = """
            INSERT INTO tb_ticket (folio, registrado, id_departamento, id_usuario, id_status, id_prioridad, descripcion, titulo)
            VALUES (?, NOW(), ?, ?, ?, ?, ?, ?)
        """;

        try (Connection con = new MySqlConexion().getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, folio);
            ps.setInt(2, idDepartamento);
            ps.setInt(3, idUsuario);
            ps.setInt(4, idEstado);
            ps.setInt(5, idPrioridad);
            ps.setString(6, descripcion);
            ps.setString(7, titulo);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error al registrar ticket: " + e.getMessage());
            return false;
        }
    }

    // Listar departamentos
    public List<String> getDepartamentos() {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT nombre FROM tb_departamento";
        try (Connection con = new MySqlConexion().getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al cargar departamentos: " + e.getMessage());
        }
        return lista;
    }

    // Listar estados
    public List<String> getEstados() {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT nombre FROM tb_estados";
        try (Connection con = new MySqlConexion().getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al cargar estados: " + e.getMessage());
        }
        return lista;
    }

    // Listar prioridades
    public List<String> getPrioridades() {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT nombre FROM tb_prioridades";
        try (Connection con = new MySqlConexion().getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al cargar prioridades: " + e.getMessage());
        }
        return lista;
    }
}
