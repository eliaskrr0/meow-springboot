package meow.micro.manager.recipe.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Object id) {
        super("No se puede recuperar obtener la receta con el id: " + id);
    }
}
