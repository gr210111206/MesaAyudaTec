package dao;

import mesaayudatec.newpackage.model.MySqlConexion;
import java.sql.*;

public class LoginDAO {

    // ------------------ CLASE INTERNA ------------------
    public static class UserInfo {
        public final int idUsuario;
        public final String nombre;
        public final String rol;
        public final int idDepartamento;
        public final int permisoRegistrar;
        public final int permisoSeguimiento;

        public UserInfo(int idUsuario, String nombre, String rol, int idDepartamento,
                        int permisoRegistrar, int permisoSeguimiento) {
            this.idUsuario = idUsuario;
            this.nombre = nombre;
            this.rol = rol;
            this.idDepartamento = idDepartamento;
            this.permisoRegistrar = permisoRegistrar;
            this.permisoSeguimiento = permisoSeguimiento;
        }
    }

    // ------------------ LOGIN ------------------
    public UserInfo login(String usuario, String password) {
        String sql = """
            SELECT u.idusuario, u.nombre, r.nombre AS rol, u.id_departamento,
                   r.permiso_registrar, r.permiso_seguimiento
            FROM tb_usuario u
            JOIN tb_roles r ON r.id = u.id_rol
            WHERE u.usuario = ?
              AND u.password_usuario = UNHEX(SHA2(?, 256))
              AND u.inhabilitado = 0
            LIMIT 1
        """;

        try (Connection con = new MySqlConexion().getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new UserInfo(
                        rs.getInt("idusuario"),
                        rs.getString("nombre"),
                        rs.getString("rol"),
                        rs.getInt("id_departamento"),
                        rs.getInt("permiso_registrar"),
                        rs.getInt("permiso_seguimiento")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Error en login: " + e.getMessage());
        }
        return null;
    }

    // ------------------ OBTENER USUARIO POR ID ------------------
    public UserInfo getUserById(int idUsuario) {
        String sql = """
            SELECT u.idusuario, u.nombre, r.nombre AS rol, u.id_departamento,
                   r.permiso_registrar, r.permiso_seguimiento
            FROM tb_usuario u
            JOIN tb_roles r ON r.id = u.id_rol
            WHERE u.idusuario = ?
            LIMIT 1
        """;

        try (Connection con = new MySqlConexion().getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new UserInfo(
                        rs.getInt("idusuario"),
                        rs.getString("nombre"),
                        rs.getString("rol"),
                        rs.getInt("id_departamento"),
                        rs.getInt("permiso_registrar"),
                        rs.getInt("permiso_seguimiento")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Error en getUserById: " + e.getMessage());
        }
        return null;
    }
}
