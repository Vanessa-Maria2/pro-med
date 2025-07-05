package br.edu.ufrn.promed.mapper;

import br.edu.ufrn.promed.model.Paciente;
import br.edu.ufrn.promed.model.PacienteRequestDto;
import br.edu.ufrn.promed.model.PacienteResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PacienteMapper {

    @Mapping(source = "pessoaDto", target = "pessoa")
    Paciente toPaciente(PacienteRequestDto pacienteRequestDto);

    PacienteResponseDto toPacienteResponseDto(Paciente paciente);
}
