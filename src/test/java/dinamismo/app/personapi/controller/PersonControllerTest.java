package dinamismo.app.personapi.controller;

import dinamismo.app.personapi.builder.PersonDTOBuilder;
import dinamismo.app.personapi.dto.MessageResponseDTO;

import dinamismo.app.personapi.dto.request.PersonDTO;
import dinamismo.app.personapi.service.PersonService;
import dinamismo.app.personapi.service.exception.PersonNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Collections;

import static dinamismo.app.personapi.helper.JsonConvertionHelper.asJsonString;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {

    private static final String PERSON_URL_PATH = "/api/v2/people";
    private static final Long VALID_PERSON_ID = 1L;
    private static final Long INVALID_PERSON_ID = 2L;

    private MockMvc mockMvc;

    @Mock
    private PersonService personService;

    @InjectMocks
    private PersonController personController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(personController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void whenPOSTIsCalledThenAPersonIsCreated() throws Exception {
        //given 
        PersonDTO expectedSavedPerson = PersonDTOBuilder.builder().build().toPersonDTO();
        MessageResponseDTO expectedSuccessMessage = 
                createExceptedMessageResponse(expectedSavedPerson.getId(), "Created INSS TAX with ID ");

        //when
        when(personService.createPerson(expectedSavedPerson)).thenReturn(expectedSuccessMessage);
        
        //then
        mockMvc.perform(post(PERSON_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(expectedSavedPerson)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message", is(expectedSuccessMessage.getMessage())));
    }

    @Test
    void whenPOSTIsCalledWithouRequiredFieldThenAnErrorIsReturned() throws Exception {
        //given
        PersonDTO expectedSavedPerson = PersonDTOBuilder.builder().build().toPersonDTO();
        
        expectedSavedPerson.setFirstName("");
        
        //then
        mockMvc.perform(post(PERSON_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(expectedSavedPerson)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenGETIsCalledWithValidIdThenOkStatusIsReturned() throws Exception {
        //given
        PersonDTO personToSave = PersonDTOBuilder.builder().build().toPersonDTO();
        PersonDTO savedPerson  = PersonDTOBuilder.builder().build().toPersonDTO();
        //when
        when(personService.findById(personToSave.getId())).thenReturn(savedPerson);
        //then
        mockMvc.perform(get(PERSON_URL_PATH + "/" + personToSave.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(personToSave.getFirstName())))
                .andExpect(jsonPath("$.cpf", is(personToSave.getCpf ())));         
    }
    
    @Test
    void whenGETListWithPeoploIsCalledThenOkStatusIsReturned() throws Exception {
        PersonDTO personDTO = PersonDTOBuilder.builder().build().toPersonDTO();

        when(personService.listAll()).thenReturn(Collections.singletonList(personDTO));

        mockMvc.perform(get(PERSON_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is(personDTO.getFirstName())))
                .andExpect(jsonPath("$[0].cpf", is(personDTO.getCpf())))
                .andExpect(jsonPath("$[0].birthDate", is(personDTO.getBirthDate())));
    }
    
    @Test
    void whenGETListWithoutPersonIsCalledThenOkStatusIsReturned() throws Exception {
        when(personService.listAll()).thenReturn(Collections.EMPTY_LIST);

        mockMvc.perform(get(PERSON_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    
    void whenGETPersonByCpfIfFoundThenPersonIsReturnWithStatusOK() throws Exception {
        PersonDTO expectedFoundCpf    = PersonDTOBuilder.builder().build().toPersonDTO();
        PersonDTO foundSavedPerson = PersonDTOBuilder.builder().build().toPersonDTO();
        
        when(personService.findByCfp(expectedFoundCpf.getCpf())).thenReturn(foundSavedPerson);

        mockMvc.perform(get(PERSON_URL_PATH+"/cpf/"+expectedFoundCpf.getCpf())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is(foundSavedPerson.getFirstName())))
                .andExpect(jsonPath("$[0].cpf", is(foundSavedPerson.getCpf())))
                .andExpect(jsonPath("$[0].birthDate", is(foundSavedPerson.getBirthDate())));
    }
    
    
    @Test
    void whenPUTIsCalledThenPersonIsUpdate() throws Exception {
        PersonDTO expectedSavedPerson = PersonDTOBuilder.builder().build().toPersonDTO();

        MessageResponseDTO expectedSuccessMessage = 
                createExceptedMessageResponse(expectedSavedPerson.getId(), "Update PERSON with ID ");

        when(personService.updateById(VALID_PERSON_ID, expectedSavedPerson)).thenReturn(expectedSuccessMessage);

        mockMvc.perform(put(PERSON_URL_PATH+"/"+expectedSavedPerson.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(expectedSavedPerson)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is(expectedSuccessMessage.getMessage())));
    }

    @Test
    void whenDELETEIsCalledWithValidIdThenNoContentStatusIsReturned() throws Exception {
        doNothing().when(personService).deleteById(VALID_PERSON_ID);

        mockMvc.perform(delete( PERSON_URL_PATH+ "/" + VALID_PERSON_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(personService, times(1)).deleteById(VALID_PERSON_ID);
    }
    
    @Test
    void whenDELETEIsCalledWithoutValidIdThenNotFoundStatusIsReturned() throws Exception {
        doThrow(PersonNotFoundException.class).when(personService).deleteById(INVALID_PERSON_ID);

        mockMvc.perform(delete(PERSON_URL_PATH + "/" + INVALID_PERSON_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    
    private MessageResponseDTO createExceptedMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }
}
