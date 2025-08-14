package meow.micro.user.profile.utils.messages;

public class ValidationMessages {
    public static final String COLUMN_ID_SCHEMA = "ID del perfil del usuario";

    public static final String COLUMN_NAME_SCHEMA = "Nombre del perfil del usuario";
    public static final String COLUMN_NAME_PATTERN = "El nombre solo puede contener letras y espacios";
    public static final String COLUMN_NAME_SIZE = "El nombre debe tener entre 2 y 30 caracteres";
    public static final String COLUMN_NAME_NOT_BLANK = "El nombre no puede venir vacío";

    public static final String COLUMN_AGE_SCHEMA = "Edad del usuario";
    public static final String COLUMN_AGE_REQUIRED = "La edad es obligatoria";
    public static final String COLUMN_AGE_RANGE = "La edad debe estar entre 1 y 3 dígitos";

    public static final String COLUMN_HEIGHT_SCHEMA = "Altura del usuario";
    public static final String COLUMN_HEIGHT_REQUIRED = "La altura es obligatoria";
    public static final String COLUMN_HEIGHT_RANGE = "La altura debe estar entre 2 y 3 dígitos";

    public static final String COLUMN_TYPE_TARGET_SCHEMA = "Objetivo del usuario";
    public static final String COLUMN_TYPE_TARGET_REQUIRED = "Debe seleccionar un objetivo";

    public static final String COLUMN_GENDER_SCHEMA = "Genero del usuario";
    public static final String COLUMN_GENDER_REQUIRED = "Debe seleccionar un genero";

    public static final String FRONTEND_URL = "http://localhost:3000";
    public static final String[] ALLOWED_METHODS = {"GET", "POST", "PUT", "DELETE"};
    public static final String[] ALLOWED_HEADERS = {"*"};
}
