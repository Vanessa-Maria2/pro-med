package br.edu.ufrn.promed.mapper;

import br.edu.ufrn.promed.dto.response.PessoaResponseDto;
import br.edu.ufrn.promed.model.Pessoa;
import br.edu.ufrn.promed.dto.request.PessoaRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PessoaMapper {

    Pessoa toPessoa(PessoaRequestDto pessoaDto);

    PessoaResponseDto toPessoaResponseDto(Pessoa pessoa);
}
