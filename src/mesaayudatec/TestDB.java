
package mesaayudatec;
import java.sql.*;
public class TestDB {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/bdmesaayudatec?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "1720";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            System.out.println("✅ Conectado a la base de datos!");
        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}