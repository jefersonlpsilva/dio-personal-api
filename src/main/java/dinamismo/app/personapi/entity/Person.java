package dinamismo.app.personapi.entity;

import dinamismo.app.personapi.enums.Gender;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String firstName;
    
    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String cpf;
    
    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate birthDate;
    
    private String zipCode;
    
    private String state;

    private String city;
    
    private String address;
    
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @Singular private List<Phone> phones;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Singular
    private List<JobInformation> jobInformations;
    
}