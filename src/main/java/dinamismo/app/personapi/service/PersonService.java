package dinamismo.app.personapi.service;

import dinamismo.app.personapi.dto.MessageResponseDTO;
import dinamismo.app.personapi.entity.Person;
import dinamismo.app.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    private PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }
    
    public MessageResponseDTO createPerson(Person person){
        Person savedPerson  = personRepository.save(person);
        return MessageResponseDTO.
                builder().
                message("Created person with id"+savedPerson.getId()).
                build();
    }
}
