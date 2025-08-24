package meow.micro.user.goal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Object id) {
        super("No se puede obtener los objetivos del usuario con el id: " + id);
    }
}
