package br.edu.ufrn.promed.mapper;

import br.edu.ufrn.promed.dto.request.ConsultaRequestDto;
import br.edu.ufrn.promed.dto.response.ConsultaResponseDto;
import br.edu.ufrn.promed.model.Consulta;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConsultaMapper {

    Consulta toConsulta(ConsultaRequestDto consultaRequestDto);

    ConsultaResponseDto toConsultaResponseDto(Consulta consulta);
}
