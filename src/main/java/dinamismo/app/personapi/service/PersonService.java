package dinamismo.app.personapi.service;

import dinamismo.app.personapi.dto.MessageResponseDTO;
import dinamismo.app.personapi.dto.request.PersonDTO;
import dinamismo.app.personapi.entity.Person;
import dinamismo.app.personapi.mapper.PersonMapper;
import dinamismo.app.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {
    
    private PersonRepository personRepository;
    
    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    
    public MessageResponseDTO createPerson(PersonDTO personDTO){
        Person personToSave  = personMapper.toModel(personDTO);
        Person savedPerson  = personRepository.save(personToSave);
        return MessageResponseDTO.
                builder().
                message("Created person with ID "+savedPerson.getId()).
                build();
    }

    public List<PersonDTO> listAll() {
        List<Person> allPeoplo = personRepository.findAll();
        return allPeoplo.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }
    
}
