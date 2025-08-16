package meow.micro.manager.food.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Object id) {
        super("No se puede recuperar obtener el alimento con el id: " + id);
    }
}
