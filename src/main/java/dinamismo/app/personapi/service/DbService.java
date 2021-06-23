package dinamismo.app.personapi.service;

import dinamismo.app.personapi.entity.JobInformation;
import dinamismo.app.personapi.entity.Person;
import dinamismo.app.personapi.entity.Phone;
import dinamismo.app.personapi.enums.Gender;
import dinamismo.app.personapi.enums.PhoneType;
import dinamismo.app.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

@Service
public class DbService {
    
    @Autowired
    private PersonRepository personRepository;
    
    public void inicializeTestDataBase(){

        JobInformation firstPersonJobInformation = JobInformation.builder()
                .id(null)
                .occupation("Event Security")
                .employmentContract("Event Security Contract")
                .firstDate(LocalDate.of(1962,9,21))
                .lastDate(null)
                .managerId(8L)
                .numberEligibleDependent(3)
                .payRate(5000.0)
                .hourByPayRate(220.0)
                .workLocation("Staff Event Security")
                .build();
        
        Person firstPerson = Person.builder()
                .id(null)
                .email("georgejetson@hotmail.com").cpf("97905087000")
                .firstName("George").lastName("Jetson").birthDate(LocalDate.of(1962,9,21))
                .gender(Gender.M)
                .zipCode("10023-6298").state("New York").city("New York").address("77 West 66th Street Fifth Floor")
                .phone(new Phone(null, PhoneType.MOBILE, "5551998605159") )
                .jobInformation(firstPersonJobInformation)
                .build();

        JobInformation secondPersonJobInformation = JobInformation.builder()
                .id(null)
                .occupation("Security Officer")
                .employmentContract("Security Contract")
                .firstDate(LocalDate.of(1962,9,21))
                .lastDate(null)
                .managerId(1L)
                .numberEligibleDependent(0)
                .payRate(5000.0)
                .hourByPayRate(220.0)
                .workLocation("House")
                .build();

        Person secondPerson = Person.builder()
                .id(null)
                .email("janejetson@hotmail.com").cpf("31361890002")
                .firstName("Jane").lastName("Jetson").birthDate(LocalDate.of(1969,9,2))
                .gender(Gender.F)
                .zipCode("10023-6298").state("New York").city("New York").address("77 West 66th Street Fifth Floor")
                .jobInformation(secondPersonJobInformation)
                .phone(new Phone(null, PhoneType.MOBILE, "5551998605159"))
                .build();

        JobInformation thirdPersonJobInformation = JobInformation.builder()
                .id(null)
                .occupation("Security Officer")
                .employmentContract("Security Contract")
                .firstDate(LocalDate.of(1962,9,21))
                .lastDate(null)
                .managerId(1L)
                .numberEligibleDependent(0)
                .payRate(5000.0)
                .hourByPayRate(220.0)
                .workLocation("House")
                .build();
        

        Person thirdPerson = Person.builder()
                .id(null)
                .email("judyjetson@hotmail.com").cpf("09966342079")
                .firstName("Judy").lastName("Jetson").birthDate(LocalDate.of(1987,8,3))
                .gender(Gender.F)
                .zipCode("10023-6298").state("New York").city("New York").address("77 West 66th Street Fifth Floor")
                .jobInformation(thirdPersonJobInformation)
                .build();

        JobInformation fourthPersonJobInformation = JobInformation.builder()
                .id(null)
                .occupation("Unarmed Security Officer")
                .employmentContract("Security Contract")
                .firstDate(LocalDate.of(1962,9,21))
                .lastDate(null)
                .managerId(1L)
                .numberEligibleDependent(0)
                .payRate(5000.0)
                .hourByPayRate(220.0)
                .workLocation("House")
                .build();
        
        Person fourthPerson = Person.builder()
                .id(null)
                .email("elroyjetson@hotmail.com").cpf("09573735032")
                .firstName("Elroy").lastName("Jetson").birthDate(LocalDate.of(1996,11,17))
                .gender(Gender.M)
                .zipCode("10023-6298").state("New York").city("New York").address("77 West 66th Street Fifth Floor")
                .jobInformation(fourthPersonJobInformation)
                .phone(new Phone(null, PhoneType.MOBILE, "5551998605159") )
                .build();

        JobInformation fifthPersonJobInformation = JobInformation.builder()
                .id(null)
                .occupation("Coordinator Security")
                .employmentContract("Security Contract")
                .firstDate(LocalDate.of(1962,9,21))
                .lastDate(null)
                .managerId(1L)
                .numberEligibleDependent(0)
                .payRate(5000.0)
                .hourByPayRate(220.0)
                .workLocation("House")
                .build();         

        Person fifthPerson = Person.builder()
                .id(null)
                .email("rosierobo@hotmail.com").cpf("73543787047")
                .firstName("Rosie").lastName("Robo").birthDate(LocalDate.of(1960,10,1))
                .gender(Gender.F)
                .zipCode("10023-6298").state("New York").city("New York").address("77 West 66th Street Fifth Floor")
                .jobInformation(fifthPersonJobInformation)
                .build();

        JobInformation sixthPersonJobInformation = JobInformation.builder()
                .id(null)
                .occupation("Coordinator Security")
                .employmentContract("Security Contract")
                .firstDate(LocalDate.of(1962,9,21))
                .lastDate(null)
                .managerId(1L)
                .numberEligibleDependent(0)
                .payRate(5000.0)
                .hourByPayRate(220.0)
                .workLocation("House")
                .build();
        

        Person sixthPerson = Person.builder()
                .id(null)
                .email("astrodog@hotmail.com").cpf("76942087084")
                .firstName("Astro").lastName("Dog").birthDate(LocalDate.of(1984,11,29))
                .gender(Gender.M)
                .zipCode("10023-6298").state("New York").city("New York").address("77 West 66th Street Fifth Floor")
                .jobInformation(sixthPersonJobInformation)
                .build();

        Person seventhPerson = Person.builder()
                .id(null)
                .email("orbittyallien@hotmail.com").cpf("98696706013")
                .firstName("Orbitty").lastName("Allien").birthDate(LocalDate.of(1980,1,1))
                .gender(Gender.M)
                .zipCode("10023-6298").state("New York").city("New York").address("77 West 66th Street Fifth Floor")
                .build();


        JobInformation eighthPersonJobInformation = JobInformation.builder()
                .id(null)
                .occupation("The Big Boss")
                .employmentContract("Security Contract")
                .firstDate(LocalDate.of(1962,9,21))
                .lastDate(null)
                .managerId(1L)
                .numberEligibleDependent(0)
                .payRate(5000.0)
                .hourByPayRate(220.0)
                .workLocation("House")
                .build();

        Person eighthPerson = Person.builder()
                .id(null)
                .email("misterspacely@hotmail.com").cpf("")
                .firstName("Mister").lastName("Spacely").birthDate(LocalDate.of(1980,1,1))
                .gender(Gender.M)
                .zipCode("10023-6298").state("New York").city("New York").address("77 West 66th Street Fifth Floor")
                .jobInformation(eighthPersonJobInformation)
                .phone(new Phone(null, PhoneType.MOBILE, "5551998605159"))
                .build();


        personRepository.saveAll(Arrays.asList(firstPerson, secondPerson, thirdPerson,
                fourthPerson, fifthPerson, sixthPerson, seventhPerson, eighthPerson));
        
        
        
        
    }
}
