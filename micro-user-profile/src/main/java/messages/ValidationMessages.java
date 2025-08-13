package messages;

public class ValidationMessages {
    public static final String COLUMN_ID_SCHEMA = "ID del perfil del usuario";

    public static final String COLUMN_NAME_SCHEMA = "Nombre del perfil del usuario";
    public static final String COLUMN_NAME_PATTERN = "El nombre solo puede contener letras y espacios";
    public static final String COLUMN_NAME_SIZE = "El nombre debe tener entre 2 y 50 caracteres";
    public static final String COLUMN_NAME_NOT_BLANK = "El nombre no puede venir vacío";

    public static final String FRONTEND_URL = "http://localhost:3000";
    public static final String[] ALLOWED_METHODS = {"GET", "POST", "PUT", "DELETE"};
    public static final String[] ALLOWED_HEADERS = {"*"};
}
