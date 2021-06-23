package dinamismo.app.personapi.builder;


import dinamismo.app.personapi.dto.request.JobInformationDTO;
import dinamismo.app.personapi.dto.request.PersonDTO;
import dinamismo.app.personapi.dto.request.PhoneDTO;
import dinamismo.app.personapi.enums.Gender;
import dinamismo.app.personapi.enums.PhoneType;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
public class PersonDTOBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String email = "silvajefersonluis@hotmail.com";

    @Builder.Default
    private String firstName = "Jeferson";

    @Builder.Default
    private String lastName = "Silva";

    @Builder.Default
    private String cpf = "522956130-00";

    @Builder.Default
    private String birthDate = "17-11-1977";

    @Builder.Default
    private Gender gender = Gender.M; 

    @Builder.Default
    private String zipCode = "93010-170";

    @Builder.Default
    private String state = "Rio Grande do Sul";

    @Builder.Default
    private String city = "Sao Leopoldo";

    @Builder.Default
    private String address = "Rua Sao Paulo, 319, apto 202, Centro";

    @Builder.Default
    private List<PhoneDTO> phones = new ArrayList<PhoneDTO>(){{
        add(new PhoneDTO(1L, PhoneType.MOBILE, "5551-998605001"));
        add(new PhoneDTO(2L, PhoneType.HOME, "5551-998605002"));
        add(new PhoneDTO(3L, PhoneType.COMERCIAL, "5551-998605003"));
    }};

    @Builder.Default
    private List<JobInformationDTO> jobInformations = new ArrayList<JobInformationDTO>(){{
        add(new JobInformationDTO(1L, "Security Guard", 3L, "Basic Labor Contract", "New York - Hell's Kitchen","31-01-2020","", 4000.0, 200.0, 1));

    }};
    

    public PersonDTO toPersonDTO() {
        return new PersonDTO(id,
                email,
                firstName,
                lastName,
                cpf,
                birthDate,
                gender,
                zipCode,
                state,
                city,
                address,
                phones,
                jobInformations);
    }
}