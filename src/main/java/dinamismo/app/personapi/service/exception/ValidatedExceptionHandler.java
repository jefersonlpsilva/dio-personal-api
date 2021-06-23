package dinamismo.app.personapi.service.exception;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice(annotations = Validated.class)
public class ValidatedExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> handle(ConstraintViolationException exception) {

        List<String> errors = exception.getConstraintViolations()
                .stream()
                .map(this::toString)
                .collect(Collectors.toList());
        
        return new ResponseEntity<>(new ErrorResponseBody(exception.getLocalizedMessage(), errors),
                HttpStatus.BAD_REQUEST);
    }

    private String toString(ConstraintViolation<?> violation) {
        return String.format("%s %s: %s",
                violation.getRootBeanClass().getName(),
                violation.getPropertyPath(),
                violation.getMessage());
    }

    public static class ErrorResponseBody {
        private String message;
        private List<String> errors;
        
        public ErrorResponseBody(String message, List<String> errors) {
            this.message = message;
            this.errors = errors;
        } 
    }
}