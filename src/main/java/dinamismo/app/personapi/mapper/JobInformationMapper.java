package dinamismo.app.personapi.mapper;

import dinamismo.app.personapi.dto.request.JobInformationDTO;
import dinamismo.app.personapi.entity.JobInformation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {DateMapper.class})
public interface JobInformationMapper {
    
    JobInformationMapper INSTANCE = Mappers.getMapper(JobInformationMapper.class);
    
    JobInformation toModel(JobInformationDTO jobInformationDTO);

    JobInformationDTO toDTO(JobInformation jobInformation);
}
