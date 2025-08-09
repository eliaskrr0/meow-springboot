package meow.micromanagerrecipe.utils.messages;

public class ValidationMessages {
    public static final String COLUMN_ID_SCHEMA = "ID de la receta";

    public static final String COLUMN_NAME_SCHEMA = "Nombre de la receta";
    public static final String COLUMN_NAME_PATTERN = "El nombre solo puede contener letras y espacios";
    public static final String COLUMN_NAME_SIZE = "El nombre debe tener entre 2 y 50 caracteres";
    public static final String COLUMN_NAME_NOT_BLANK = "El nombre no puede venir vacío";

    public static final String COLUMN_AMOUNT_SCHEMA = "Cantidad de la receta";
    public static final String COLUMN_AMOUNT_REQUIRED = "La cantidad es obligatoria";
    public static final String COLUMN_AMOUNT_POSITIVE = "La cantidad debe ser mayor que 0";

    public static final String COLUMN_FOOD_ID_SCHEMA = "ID del alimento";
    public static final String COLUMN_FOOD_ID_REQUIRED = "El ID del alimento es obligatorio";

    public static final String FRONTEND_URL = "http://localhost:3000";
    public static final String[] ALLOWED_METHODS = {"GET", "POST", "PUT", "DELETE"};
    public static final String[] ALLOWED_HEADERS = {"*"};

}
