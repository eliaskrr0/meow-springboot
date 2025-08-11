package meow.micromanagerfood.utils.messages;

public class ValidationMessages {
    public static final String COLUMN_ID_SCHEMA = "ID del alimento";

    public static final String COLUMN_NAME_SCHEMA = "Nombre del alimento";
    public static final String COLUMN_NAME_PATTERN = "El nombre solo puede contener letras y espacios";
    public static final String COLUMN_NAME_SIZE = "El nombre debe tener entre 2 y 50 caracteres";
    public static final String COLUMN_NAME_NOT_BLANK = "El nombre no puede venir vacío";

    public static final String COLUMN_BRAND_SCHEMA = "Marca del alimento";
    public static final String COLUMN_BRAND_PATTERN = "El nombre de la marca solo puede contener letras y espacios";
    public static final String COLUMN_BRAND_SIZE = "El nombre de la marca debe tener entre 2 y 50 caracteres";
    public static final String COLUMN_BRAND_NOT_BLANK = "El nombre de la marca no puede venir vacío";

    public static final String COLUMN_UNIT_MEASURE_SCHEMA = "Unidad del alimento";
    public static final String COLUMN_UNIT_MEASURE_REQUIRED = "Debe seleccionar una unidad: gr o ml";

    public static final String COLUMN_AMOUNT_SCHEMA = "Cantidad del alimento";
    public static final String COLUMN_AMOUNT_REQUIRED = "La cantidad es obligatoria";
    public static final String COLUMN_AMOUNT_POSITIVE = "La cantidad debe ser mayor que 0";

    public static final String COLUMN_PROTEIN_AMOUNT_SCHEMA = "Cantidad de proteínas que tiene el alimento";
    public static final String COLUMN_PROTEIN_AMOUNT_REQUIRED = "La cantidad de proteína es obligatoria";
    public static final String COLUMN_PROTEIN_AMOUNT_POSITIVE = "La proteína debe ser mayor que 0";

    public static final String COLUMN_CARBS_AMOUNT_SCHEMA = "Cantidad de carbos que tiene el alimento";
    public static final String COLUMN_CARBS_AMOUNT_REQUIRED = "La cantidad de carbohidratos es obligatoria";
    public static final String COLUMN_CARBS_AMOUNT_POSITIVE = "Los carbohidratos deben ser un valor positivo";

    public static final String COLUMN_FAT_AMOUNT_SCHEMA = "LCantidad de grasas que tiene el alimento";
    public static final String COLUMN_FAT_AMOUNT_REQUIRED = "La cantidad de grasa es obligatoria";
    public static final String COLUMN_FAT_AMOUNT_POSITIVE = "La grasa debe ser mayor que 0";

    public static final String COLUMN_CALORIES_SCHEMA = "Cantidad de calorías que tiene el alimento";
    public static final String COLUMN_CALORIES_REQUIRED = "Las calorías son obligatorias";
    public static final String COLUMN_CALORIES_POSITIVE = "Las calorías deben ser un valor positivo";

    public static final String COLUMN_PRICE_SCHEMA = "Precio del alimento";
    public static final String COLUMN_PRICE_POSITIVE = "El precio debe ser un valor positivo";

    public static final String FRONTEND_URL = "http://localhost:3000";
    public static final String[] ALLOWED_METHODS = {"GET", "POST", "PUT", "DELETE"};
    public static final String[] ALLOWED_HEADERS = {"*"};

}
