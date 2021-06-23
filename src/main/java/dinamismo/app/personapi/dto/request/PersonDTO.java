package dinamismo.app.personapi.dto.request;

import dinamismo.app.personapi.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.mapstruct.IterableMapping;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
    
    private Long id;

    @NotEmpty
    @Email
    private String email;
    
    @NotEmpty
    @Size(min = 2 , max = 100)
    private String firstName;

    @NotEmpty
    @Size(min = 2 , max = 100)
    private String lastName;

    @NotEmpty
    @CPF
    private String cpf;

    private String birthDate;
    
    private Gender gender;
    
    private String zipCode;
    
    private String state;
    
    private String city;
    
    private String address;
    
    @Valid
    @NotEmpty
    private List<PhoneDTO> phones;
    
    private List<JobInformationDTO> jobInformations;
}
