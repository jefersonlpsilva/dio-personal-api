package dinamismo.app.personapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class INSSTax {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;
    
    @Column(nullable = false)
    private Double salaryFrom;

    @Column(nullable = false)
    private Double salaryUpTo;

    @Column(nullable = false)   
    private Double socialSecurityRatePercent;
    
    private Double ceilingContributionCurrency;
    
}