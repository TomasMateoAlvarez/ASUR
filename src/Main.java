import controllers.UserController;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserController userController = new UserController();

        // Probar la conexión
        userController.testConnection();

        userController.crearUsuario();
    }
}