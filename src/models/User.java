package models;

import java.time.LocalDate;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDate fechaNacimiento;
    private String domicilio;
    private String telefonos;
    private String tipoDocumento;
    private TipoUser tipoUsuario; // Clase o enum TipoUser
    private CategoriaSocio categoriaSocio; // Clase o enum CategoriaSocio

    // 🔹 Constructor principal con todos los campos
    public User(int id, String firstName, String lastName, String email, String password,
                LocalDate fechaNacimiento, String domicilio, String telefonos, String tipoDocumento,
                TipoUser tipoUsuario, CategoriaSocio categoriaSocio) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.fechaNacimiento = fechaNacimiento;
        this.domicilio = domicilio;
        this.telefonos = telefonos;
        this.tipoDocumento = tipoDocumento;
        this.tipoUsuario = tipoUsuario;
        this.categoriaSocio = categoriaSocio;
    }

    // 🔹 Constructor alternativo (sin tipoUsuario ni categoriaSocio)
    public User(int id, String firstName, String lastName, String tipoDocumento, String telefonos,
                LocalDate fechaNacimiento, String domicilio, String email, String password) {
        this(id, firstName, lastName, email, password, fechaNacimiento, domicilio, telefonos, tipoDocumento, null, null);
    }

    // 🔹 Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("El email no puede estar vacío.");
        }
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(String telefonos) {
        this.telefonos = telefonos;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public TipoUser getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUser tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public CategoriaSocio getCategoriaSocio() {
        return categoriaSocio;
    }

    public void setCategoriaSocio(CategoriaSocio categoriaSocio) {
        this.categoriaSocio = categoriaSocio;
    }

    // 🔹 Métodos adicionales
    public String getNombres() {
        return this.firstName; // Retorna el nombre (firstName)
    }

    public String getApellidos() {
        return this.lastName; // Retorna el apellido (lastName)
    }

    public String getNumeroDocumento() {
        // Si tienes un atributo para el número de documento, retórnalo aquí
        // Ejemplo: return this.numeroDocumento;
        return ""; // Por ahora, retorna una cadena vacía
    }

    public String getContrasena() {
        return this.password; // Retorna la contraseña
    }

    public TipoUser getTipoUser() {
        return this.tipoUsuario; // Retorna el tipo de usuario
    }

    // 🔹 Método toString para representación en cadena
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", domicilio='" + domicilio + '\'' +
                ", telefonos='" + telefonos + '\'' +
                ", tipoDocumento='" + tipoDocumento + '\'' +
                ", tipoUsuario=" + tipoUsuario +
                ", categoriaSocio=" + categoriaSocio +
                '}';
    }
}