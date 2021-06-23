package dinamismo.app.personapi.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PersonAlreadyRegisteredException extends Exception{

    public PersonAlreadyRegisteredException(String cpf) {
        super(String.format("Person with CPF %s already registered in the system.", cpf));
    }
    
}