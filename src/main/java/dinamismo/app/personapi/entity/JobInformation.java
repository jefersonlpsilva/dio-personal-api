package dinamismo.app.personapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Long id; 
    
    @Column(nullable = false)
    private String occupation;
    
    private Long managerId;

    private String employmentContract;

    @Column(nullable = false)
    private String workLocation;
    
    private LocalDate firstDate;

    private LocalDate lastDate;

    @Column(nullable = false)
    private Double payRate;
    
    @Column(nullable = false)
    private Double hourByPayRate;

    @Column(nullable = false)
    private Integer numberEligibleDependent;
    
}
