package models;

public enum TipoUser {
    SOCIO(1, "Socio"),
    NO_SOCIO(2, "No Socio"),
    ADMINISTRADOR(3, "Administrador");

    private final int id;
    private final String nombre;

    TipoUser(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public static TipoUser fromId(int id) {
        for (TipoUser tipo : TipoUser.values()) {
            if (tipo.id == id) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("No se encontró TipoUser con id: " + id);
    }
}
