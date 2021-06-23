package dinamismo.app.personapi.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonCpfNotFoundException extends Exception {
    public PersonCpfNotFoundException(String cpf) {
        super("Person not found by Cfp " + cpf);
    }
}
