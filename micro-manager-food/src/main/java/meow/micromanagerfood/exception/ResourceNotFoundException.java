package meow.micromanagerfood.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Object id) {
        super("No se puede alimento el recurso con id: " + id);
    }
}
