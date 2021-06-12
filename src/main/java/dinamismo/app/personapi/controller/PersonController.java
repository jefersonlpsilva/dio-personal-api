package dinamismo.app.personapi.controller;

import dinamismo.app.personapi.dto.MessageResponseDTO;
import dinamismo.app.personapi.entity.Person;
import dinamismo.app.personapi.repository.PersonRepository;
import dinamismo.app.personapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/people")
public class PersonController {
    
    @Autowired
    private PersonService personService;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody Person person){
        return personService.createPerson(person);
    }
    
}
