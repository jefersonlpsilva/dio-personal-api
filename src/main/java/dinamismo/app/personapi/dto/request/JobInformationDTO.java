package dinamismo.app.personapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;  
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobInformationDTO {
    
    private Long id;
    
    @NotEmpty
    @Size(max = 50)
    private String occupation;
    
    private Long managerId;

    @Size(max = 100)
    private String employmentContract;

    @NotEmpty
    @Size(max = 50)
    private String workLocation;
    
    private String firstDate;

    private String lastDate;
     
    @NotEmpty
    private Double payRate;

    @Column(nullable = false)
    private Double hourByPayRate;

    @Column(nullable = false)
    private Integer numberEligibleDependent;
    
}
