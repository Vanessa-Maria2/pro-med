package br.edu.ufrn.promed.mapper;

import br.edu.ufrn.promed.model.Medico;
import br.edu.ufrn.promed.model.MedicoRequestDto;
import br.edu.ufrn.promed.model.MedicoResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MedicoMapper {

    Medico toMedico(MedicoRequestDto medicoRequestDto);

    MedicoResponseDto toMedicoResponseDto(Medico medico);
}
