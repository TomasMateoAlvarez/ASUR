package controllers;

import config.DatabaseConfig;
import models.User;
import services.UserService;

import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

public class UserController {

    private UserService userService;

    // Constructor de UserController
    public UserController() throws SQLException {
        var connection = DatabaseConfig.getConnection();  // Obtener la conexión
        this.userService = new UserService(connection);  // Crear la instancia de UserService
    }

    // Método para probar la conexión a la base de datos
    public void testConnection() {
        try {
            var connection = DatabaseConfig.getConnection();
            System.out.println("Conexión exitosa: " + connection);
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
    }

    // Método para crear un usuario
    public void crearUsuario() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Apellido: ");
            String apellido = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine().trim();
            if (email.isEmpty()) {
                System.out.println("El email es obligatorio. Por favor, ingréselo.");
                return; // Detener la ejecución si el email está vacío
            }
            System.out.println("Email ingresado: " + email);
            System.out.print("Contraseña: ");
            String contrasena = scanner.nextLine();
            System.out.print("Tipo Documento: ");
            String tipoDocumento = scanner.nextLine();
            System.out.print("Número Documento: ");
            String numeroDocumento = scanner.nextLine();
            System.out.print("Fecha Nacimiento (YYYY-MM-DD): ");
            String fechaStr = scanner.nextLine();

            LocalDate fechaNacimiento = null;
            if (!fechaStr.trim().isEmpty()) {
                try {
                    fechaNacimiento = LocalDate.parse(fechaStr);
                } catch (DateTimeException e) {
                    System.out.println("Formato de fecha no válido, se usará null para la fecha.");
                }
            }

            System.out.print("Domicilio: ");
            String domicilio = scanner.nextLine();
            System.out.print("Teléfonos: ");
            String telefonos = scanner.nextLine();

            // Crear un usuario con la información ingresada
            User user = new User(0, nombre, apellido, tipoDocumento, numeroDocumento, fechaNacimiento, domicilio, email, contrasena);

            System.out.println("Datos del usuario antes de enviarlo a UserService:");
            System.out.println("Nombre: " + user.getNombres());
            System.out.println("Apellido: " + user.getApellidos());
            System.out.println("Email: " + user.getEmail());  // Aquí se debe imprimir un email válido
            System.out.println("Contraseña: " + user.getContrasena());

            // Llamamos al servicio para crear el usuario
            userService.crearUsuario(user);  // Ahora lo hacemos correctamente con la instancia de userService
            System.out.println("Usuario creado con éxito.");
        } finally {
            scanner.close(); // Aseguramos cerrar el Scanner
        }
    }

    // Método principal
    public static void main(String[] args) {
        try {
            UserController controller = new UserController();  // Creamos la instancia de UserController
            controller.crearUsuario();  // Llamamos al método no estático usando la instancia
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
