package dinamismo.app.personapi.service;

import dinamismo.app.personapi.dto.MessageResponseDTO;
import dinamismo.app.personapi.dto.request.PersonDTO;
import dinamismo.app.personapi.entity.Person;
import dinamismo.app.personapi.repository.PersonRepository;
import dinamismo.app.personapi.utils.PersonUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
 
import static dinamismo.app.personapi.utils.PersonUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
    
    @Mock
    private PersonRepository personRepository;
    
    @InjectMocks
    private PersonService personService;

    @Test
    void testGivenPersonDTOThenReturnSavedMessage() {
        PersonDTO personDTO = createFakeDTO();
        Person expectedSavedPerson = createFakeEntity();
        
        //Mockito.when(personRepository.save(expectedSavedPerson)).thenReturn(expectedSavedPerson);
        when(personRepository.save(any(Person.class))).thenReturn(expectedSavedPerson);

        MessageResponseDTO expectedSuccessMessage = createExceptedMessageResponse(expectedSavedPerson.getId());
        
        MessageResponseDTO successMessage = personService.createPerson(personDTO);
        
        assertEquals(expectedSuccessMessage, successMessage);
    }

    private MessageResponseDTO createExceptedMessageResponse(Long id) {
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + id)
                .build();
    }
}
