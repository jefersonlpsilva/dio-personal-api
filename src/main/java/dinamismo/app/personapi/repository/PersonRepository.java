package dinamismo.app.personapi.repository;

import dinamismo.app.personapi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonRepository extends JpaRepository<Person, Long> {
}
