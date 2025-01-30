package repositories;
import models.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.TipoUser;
import models.CategoriaSocio;


public class UsuarioDAO {
    private Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(User user) throws SQLException {
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new SQLException("El email del usuario no puede ser NULL o vacío.");
        }
        String sql = "INSERT INTO Usuario (nombres, apellidos, tipoDocumento, numeroDocumento, fechaNacimiento, domicilio, email, contrasena, tipoUsuarioId, categoriaSocioId) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getNombres());
            stmt.setString(2, user.getApellidos());
            stmt.setString(3, user.getTipoDocumento());
            stmt.setString(4, user.getNumeroDocumento());

            // Validar fecha de nacimiento
            if (user.getFechaNacimiento() != null) {
                stmt.setDate(5, Date.valueOf(user.getFechaNacimiento()));
            } else {
                stmt.setNull(5, Types.DATE);
            }

            stmt.setString(6, user.getDomicilio());
            stmt.setString(7, user.getEmail());
            stmt.setString(8, user.getContrasena());

            // Validar TipoUser
            if (user.getTipoUser() != null) {
                stmt.setInt(9, user.getTipoUser().getId());
            } else {
                stmt.setNull(9, Types.INTEGER); // Asignar NULL si TipoUser es null
            }

            // Validar CategoriaSocio
            if (user.getCategoriaSocio() != null) {
                stmt.setInt(10, user.getCategoriaSocio().getId());
            } else {
                stmt.setNull(10, Types.INTEGER); // Asignar NULL si CategoriaSocio es null
            }

            stmt.executeUpdate();
        }
    }


    public User findById(int id) throws SQLException {
        String sql = "SELECT * FROM User WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("id"),
                            rs.getString("nombres"),
                            rs.getString("apellidos"),
                            rs.getString("tipoDocumento"),
                            rs.getString("numeroDocumento"),
                            rs.getDate("fechaNacimiento").toLocalDate(),
                            rs.getString("domicilio"),
                            rs.getString("email"),
                            rs.getString("contrasena"),
                            // Estos métodos dependerían de la implementación de tus relaciones
                            obtenerTipoUser(rs.getInt("tipoUser")),
                            obtenerCategoriaSocio(rs.getInt("categoriaSocio"))
                    );
                }
            }
        }
        return null;
    }

    public List<User> findAll() throws SQLException {
        List<User> Users = new ArrayList<>();
        String sql = "SELECT * FROM User";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Users.add(new User(
                        rs.getInt("id"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getString("tipoDocumento"),
                        rs.getString("numeroDocumento"),
                        rs.getDate("fechaNacimiento").toLocalDate(),
                        rs.getString("domicilio"),
                        rs.getString("email"),
                        rs.getString("contrasena"),
                        obtenerTipoUser(rs.getInt("tipoUser")),
                        obtenerCategoriaSocio(rs.getInt("categoriaSocio"))
                ));
            }
        }
        return Users;
    }

    private TipoUser obtenerTipoUser(int id) {
        for (TipoUser tipo : TipoUser.values()) {
            if (tipo.getId() == id) { // Si tienes un método getId() en el enum
                return tipo;
            }
        }
        return null; // Retorna null si no encuentra el TipoUser
    }


    private CategoriaSocio obtenerCategoriaSocio(int id) throws SQLException {
        String sql = "SELECT * FROM CategoriaSocio WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new CategoriaSocio(rs.getInt("id"), rs.getString("nombre"), rs.getString("descripcion"));
                }
            }
        }
        return null; // Retorna null si no encuentra la CategoriaSocio
    }
    public void crearUsuario(User user) {
        try {
            // Validar datos esenciales antes de llamar al DAO
            if (user.getEmail() == null || user.getEmail().isEmpty()) {
                throw new IllegalArgumentException("El email no puede estar vacío.");
            }
            if (user.getNombres() == null || user.getNombres().isEmpty()) {
                throw new IllegalArgumentException("El nombre no puede estar vacío.");
            }

            // Instancia del DAO (esto puede estar ya configurado en el servicio)
            UsuarioDAO usuarioDAO = new UsuarioDAO(connection);

            // Llamada al método `create` en el DAO
            System.out.println("🛠 Insertando usuario con email: " + user.getEmail());

            usuarioDAO.create(user);
            System.out.println("Usuario creado con éxito.");
        } catch (SQLException e) {
            // Registrar el error y relanzarlo como RuntimeException
            e.printStackTrace();
            throw new RuntimeException("Error al crear el usuario: " + e.getMessage());
        }
    }
}

