package br.edu.ufrn.promed.mapper;

import br.edu.ufrn.promed.model.Medico;
import br.edu.ufrn.promed.dto.request.MedicoRequestDto;
import br.edu.ufrn.promed.dto.response.MedicoResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MedicoMapper {

    @Mapping(source = "pessoaRequestDto", target = "pessoa")
    Medico toMedico(MedicoRequestDto medicoRequestDto);

    MedicoResponseDto toMedicoResponseDto(Medico medico);
}
