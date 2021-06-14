package dinamismo.app.personapi.utils;

import dinamismo.app.personapi.dto.request.PersonDTO;
import dinamismo.app.personapi.entity.Person;

import java.time.LocalDate;
import java.util.Collections;

public class PersonUtils {

    private static final String FIRST_NAME = "Jefeson";
    private static final String LAST_NAME = "Silva";
    private static final String CPF_NUMBER = "760.641.680-05";
    private static final long PERSON_ID = 1L;
    public static final LocalDate BIRTH_DATE = LocalDate.of(2010,10, 01);
    
    public static PersonDTO createFakeDTO() {
        return PersonDTO.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF_NUMBER)
                .birthDate("04-04-2010")
                .phones(Collections.singletonList(PhoneUtils.createFakeDTO()))
                .build();
    } 
    
    public static Person createFakeEntity() {
        return Person.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF_NUMBER)
                .birthDate(BIRTH_DATE)
                .phones(Collections.singletonList(PhoneUtils.createFakeEntity()))
                .build();
    }
    
    
}
