package dinamismo.app.personapi.repository;

import dinamismo.app.personapi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByCpf(String cpf);
    
}
