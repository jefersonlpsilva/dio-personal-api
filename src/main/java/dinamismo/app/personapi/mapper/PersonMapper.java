package dinamismo.app.personapi.mapper;

import dinamismo.app.personapi.dto.request.PersonDTO;
import dinamismo.app.personapi.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(uses = {DateMapper.class})
public interface PersonMapper {
    
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);
    
    Person toModel(PersonDTO personDTO);
    
    PersonDTO toDTO(Person person);
    
}
