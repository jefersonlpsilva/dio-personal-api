package dinamismo.app.personapi.service;

import dinamismo.app.personapi.builder.PersonDTOBuilder;
import dinamismo.app.personapi.dto.MessageResponseDTO;
import dinamismo.app.personapi.dto.request.PersonDTO;
import dinamismo.app.personapi.entity.Person;
import dinamismo.app.personapi.mapper.PersonMapper;
import dinamismo.app.personapi.repository.PersonRepository;
import dinamismo.app.personapi.service.exception.PersonCpfNotFoundException;
import dinamismo.app.personapi.service.exception.PersonAlreadyRegisteredException;
import dinamismo.app.personapi.service.exception.PersonNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest  {

    private PersonMapper personMapper = PersonMapper.INSTANCE;
    
    @Mock
    private PersonRepository personRepository;
    
    @InjectMocks
    private PersonService personService;

    @Test
    void whenPersonDTOThenReturnSuccessSavedMessage() throws PersonAlreadyRegisteredException {
        PersonDTO personDTO = PersonDTOBuilder.builder().build().toPersonDTO();
        Person expectedSavedPerson = personMapper.toModel(PersonDTOBuilder.builder().build().toPersonDTO());
        
        when(personRepository.save(any(Person.class))).thenReturn(expectedSavedPerson);

        MessageResponseDTO expectedSuccessMessage = createExceptedMessageResponse(expectedSavedPerson.getId());
        MessageResponseDTO successMessage = personService.createPerson(personDTO);
        
        assertEquals(expectedSuccessMessage, successMessage);
    }

    @Test
    void whenNotExistsPersonCpfThenReturnException() {
        Person expectedFoundPerson   =  personMapper.toModel(PersonDTOBuilder.builder().build().toPersonDTO());
        
        when(personRepository.findByCpf(expectedFoundPerson.getCpf()))
                .thenReturn(Optional.ofNullable(any(Person.class)));

        assertThrows(PersonCpfNotFoundException.class, () -> personService.findByCfp(expectedFoundPerson.getCpf()));
    }

    @Test
    void whenCpfPersonAlreadyRegisteredThenReturnPerson() throws PersonCpfNotFoundException {
        Person expectedPerson   =  personMapper.toModel(PersonDTOBuilder.builder().build().toPersonDTO());
        Person expectedFoundPerson   =   personMapper.toModel(PersonDTOBuilder.builder().build().toPersonDTO());
        
        when(personRepository.findByCpf(expectedPerson.getCpf())).thenReturn(Optional.of(expectedFoundPerson));

        PersonDTO foundPersonDTO = personService.findByCfp(expectedPerson.getCpf());
        
        PersonDTO expectedPersonDTO     =  personMapper.toDTO(expectedPerson);
        PersonDTO expectedFoundPersonDTO = personMapper.toDTO(expectedFoundPerson);
        
        assertThat(foundPersonDTO, is(equalTo(expectedPersonDTO)));
        assertThat(foundPersonDTO, is(equalTo(expectedFoundPersonDTO)));
    }

    
    @Test
    void whenValidPersonIdThenReturnThisPerson() throws PersonNotFoundException {
        PersonDTO expectedPersonDTO = PersonDTOBuilder.builder().build().toPersonDTO();
        Person expectedSavedPerson = personMapper.toModel(PersonDTOBuilder.builder().build().toPersonDTO());

        when(personRepository.findById(expectedSavedPerson.getId())).thenReturn(Optional.of(expectedSavedPerson));
        PersonDTO personDTO = personService.findById(expectedSavedPerson.getId());
        
        assertEquals(expectedSavedPerson.getId(), personDTO.getId());
        assertEquals(expectedSavedPerson.getFirstName(), personDTO.getFirstName());
    }

    @Test
    void whenInvalidPersonIdThenThrowException() {
        var invalidPersonId = 1L;
        when(personRepository.findById(invalidPersonId))
                .thenReturn(Optional.ofNullable(any(Person.class)));

        assertThrows(PersonNotFoundException.class, () -> personService.findById(invalidPersonId));
    }

    @Test
    void whenNoDataThenReturnAllPeopleRegistered() {
        PersonDTO personDTO = PersonDTOBuilder.builder().build().toPersonDTO();
        List<Person> expectedRegisteredPeople = Collections.singletonList(
               personMapper.toModel(personDTO)
        );
        
        when(personRepository.findAll()).thenReturn(expectedRegisteredPeople);
        
        List<PersonDTO> expectedPeopleDTOList = personService.listAll();

        assertFalse(expectedPeopleDTOList.isEmpty());
        assertEquals(expectedPeopleDTOList.get(0).getFirstName(), personDTO.getFirstName());
    }

    @Test
    void whenValidPersonIdAndUpdateInfoThenReturnSuccesOnUpdate() throws PersonNotFoundException {
        var updatedPersonId = 2L;

        PersonDTO updatePersonDTORequest = PersonDTOBuilder.builder().build().toPersonDTO();
        updatePersonDTORequest.setId(updatedPersonId);
        updatePersonDTORequest.setLastName("Jeferson updated");

        Person expectedPersonToUpdate = personMapper.toModel(PersonDTOBuilder.builder().build().toPersonDTO());
        expectedPersonToUpdate.setId(updatedPersonId);

        Person expectedPersonUpdated = personMapper.toModel(PersonDTOBuilder.builder().build().toPersonDTO());
        expectedPersonUpdated.setId(updatedPersonId);
        expectedPersonToUpdate.setLastName(updatePersonDTORequest.getLastName());

        when(personRepository.findById(updatedPersonId)).thenReturn(Optional.of(expectedPersonUpdated));
        when(personRepository.save(any(Person.class))).thenReturn(expectedPersonUpdated);

        MessageResponseDTO successMessage = personService.updateById(updatedPersonId, updatePersonDTORequest);

        assertEquals("Update person with ID 2", successMessage.getMessage());
    }

    @Test
    void whenInvalidPersonIdAndUpdateInfoThenThrowExceptionOnUpdate() {
        var invalidPersonId = 1L;

        PersonDTO updatePersonDTORequest = PersonDTOBuilder.builder().build().toPersonDTO();
        updatePersonDTORequest.setId(invalidPersonId);
        updatePersonDTORequest.setLastName("Jeferson updated");

        when(personRepository.findById(invalidPersonId))
                .thenReturn(Optional.ofNullable(any(Person.class)));

        assertThrows(PersonNotFoundException.class, () -> personService.updateById(invalidPersonId, updatePersonDTORequest));
    }

    @Test
    void whenValidPersonIdThenReturnSuccesOnDelete() throws PersonNotFoundException {
        var deletedPersonId = 1L;
        Person expectedPersonToDelete = personMapper.toModel(PersonDTOBuilder.builder().build().toPersonDTO());

        when(personRepository.findById(deletedPersonId)).thenReturn(Optional.of(expectedPersonToDelete));
        personService.deleteById(deletedPersonId);

        verify(personRepository, times(1)).deleteById(deletedPersonId);
    }

    @Test
    void whenInvalidPersonIdThenReturnSuccesOnDelete()  {
        var invalidPersonId = 1L;

        when(personRepository.findById(invalidPersonId))
                .thenReturn(Optional.ofNullable(any(Person.class)));

        assertThrows(PersonNotFoundException.class, () -> personService.deleteById(invalidPersonId));
    }

    private MessageResponseDTO createExceptedMessageResponse(Long id) {
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + id)
                .build();
    }
    
}
