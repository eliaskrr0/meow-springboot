package meow.micro.user.goal.utils.messages;

public class ValidationMessages {
    public static final String COLUMN_ID_SCHEMA = "ID de usuario";

    public static final String COLUMN_TYPE_TARGET_SCHEMA = "Objetivo del usuario";
    public static final String COLUMN_TYPE_TARGET_SIZE = "El objetivo tiene que tener entre 1 y 20 caracteres";
    public static final String COLUMN_TYPE_TARGET_NOT_BLANK = "El objetivo no puede venir vacío";

    public static final String COLUMN_ACTIVITY_RATE_SCHEMA = "Ritmo de actividad física del usuario";
    public static final String COLUMN_ACTIVITY_RATE_SIZE = "El ritmo de actividad física tiene que tener entre 1 y 20 caracteres";
    public static final String COLUMN_ACTIVITY_RATE_NOT_BLANK = "El ritmo de actividad física no puede venir vacío";

    public static final String COLUMN_PROTEIN_AMOUNT_SCHEMA = "Cantidad de proteínas que necesita consumir el usuario";
    public static final String COLUMN_PROTEIN_AMOUNT_REQUIRED = "La cantidad de proteína es obligatoria";
    public static final String COLUMN_PROTEIN_AMOUNT_POSITIVE = "La proteína debe ser mayor que 0";

    public static final String COLUMN_CARBS_AMOUNT_SCHEMA = "Cantidad de carbos que necesita consumir el usuario";
    public static final String COLUMN_CARBS_AMOUNT_REQUIRED = "La cantidad de carbohidratos es obligatoria";
    public static final String COLUMN_CARBS_AMOUNT_POSITIVE = "Los carbohidratos deben ser un valor positivo";

    public static final String COLUMN_FAT_AMOUNT_SCHEMA = "Cantidad de grasas que necesita consumir el usuario";
    public static final String COLUMN_FAT_AMOUNT_REQUIRED = "La cantidad de grasa es obligatoria";
    public static final String COLUMN_FAT_AMOUNT_POSITIVE = "La grasa debe ser mayor que 0";

    public static final String COLUMN_CALORIES_SCHEMA = "Cantidad de calorías que necesita consumir el usuario";
    public static final String COLUMN_CALORIES_REQUIRED = "Las calorías son obligatorias";
    public static final String COLUMN_CALORIES_POSITIVE = "Las calorías deben ser un valor positivo";

    public static final String FRONTEND_URL = "http://localhost:3000";
    public static final String[] ALLOWED_METHODS = {"GET", "POST", "PUT", "DELETE"};
    public static final String[] ALLOWED_HEADERS = {"*"};

}
