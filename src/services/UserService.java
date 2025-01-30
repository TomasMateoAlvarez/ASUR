package services;

import repositories.UsuarioDAO;
import models.User;
import java.sql.Connection;
import java.sql.SQLException;

public class UserService {

    private UsuarioDAO usuarioDao;

    // Constructor de UserService
    public UserService(Connection connection) {
        this.usuarioDao = new UsuarioDAO(connection);
    }

    // Método de instancia para crear un usuario con validaciones
    public void crearUsuario(User user) throws SQLException {
        // Validaciones antes de enviar al DAO
        System.out.println("📌 Verificando usuario en UserService: " + user.getEmail());

        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("El email no puede ser NULL o vacío.");
        }
        if (user.getNombres() == null || user.getNombres().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (user.getApellidos() == null || user.getApellidos().trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede estar vacío.");
        }
        if (user.getContrasena() == null || user.getContrasena().trim().isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía.");
        }

        usuarioDao.create(user);
        System.out.println("Usuario creado con éxito.");
    }
}
