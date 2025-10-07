package mesaayudatec.newpackage.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConexion {

    private final String HOST = "localhost";
    private final String PORT = "3306";
    private final String USER = "root";
    private final String PASSWORD = "1720";
    private final String DB = "bdmesaayudatec";

    private static Connection con;

    // Método para obtener la conexión
    public Connection getConexion() throws SQLException {
        String cadenaConexion = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB
                + "?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
        con = DriverManager.getConnection(cadenaConexion, USER, PASSWORD);
        return con;
    }

    // Método para verificar si la conexión es correcta
    public static boolean isConexionCorrecta() {
        try {
            MySqlConexion c = new MySqlConexion();
            c.getConexion();
            System.out.println("✅ Conexión establecida con la base de datos: " + c.DB);
            return true;
        } catch (SQLException e) {
            System.out.println("❌ Error al conectar: " + e.getMessage());
            return false;
        }
    }

    // Main solo para probar desde esta clase
    public static void main(String[] args) {
        isConexionCorrecta();
    }
}
