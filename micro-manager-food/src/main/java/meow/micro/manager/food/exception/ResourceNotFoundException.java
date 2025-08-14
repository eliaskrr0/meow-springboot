package meow.micro.manager.food.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Object id) {
        super("No se puede recuperar obtener el alimento con el id: " + id);
    }
}
