package dinamismo.app.personapi.service;

import dinamismo.app.personapi.dto.MessageResponseDTO;
import dinamismo.app.personapi.dto.request.PersonDTO;
import dinamismo.app.personapi.entity.Person;
import dinamismo.app.personapi.mapper.PersonMapper;
import dinamismo.app.personapi.repository.PersonRepository;
import dinamismo.app.personapi.service.exception.PersonCpfNotFoundException;
import dinamismo.app.personapi.service.exception.PersonAlreadyRegisteredException;
import dinamismo.app.personapi.service.exception.PersonNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {
    
    private PersonRepository personRepository;
    
    private final PersonMapper personMapper = PersonMapper.INSTANCE;
    
    public MessageResponseDTO createPerson(PersonDTO personDTO) throws PersonAlreadyRegisteredException {
        verifyIfIsAlreadyRegistered(personDTO.getCpf());
        Person personToSave  = personMapper.toModel(personDTO);
        Person savedPerson  = personRepository.save(personToSave);
        return createMessageResponse(savedPerson.getId(), "Created person with ID ");
    }

    public List<PersonDTO> listAll() {
        List<Person> allPeoplo = personRepository.findAll();
        return allPeoplo.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = verifyIfExists(id);         
        return personMapper.toDTO(person); 
    }

    public PersonDTO findByCfp(String cpf) throws PersonCpfNotFoundException {
        Person foundPerson = personRepository.findByCpf(cpf)
                .orElseThrow(() -> new PersonCpfNotFoundException(cpf));
        return personMapper.toDTO(foundPerson);
    }

    public void deleteById(Long id) throws PersonNotFoundException {
        verifyIfExists(id);
        personRepository.deleteById(id);
    }
    
    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExists(id);
        
        Person personToUpdate  = personMapper.toModel(personDTO);
        
        Person updatePerson  = personRepository.save(personToUpdate);
        return createMessageResponse(updatePerson.getId(), "Update person with ID ");
    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository
                .findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    private void verifyIfIsAlreadyRegistered(String cpf) throws PersonAlreadyRegisteredException {
        Optional<Person> optSavedBeer = personRepository.findByCpf(cpf);
        if (optSavedBeer.isPresent()) {
            throw new PersonAlreadyRegisteredException(cpf);
        }
    }
    
    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO.
                builder().
                message(message + id).
                build();
    }
}
