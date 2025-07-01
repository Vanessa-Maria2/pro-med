package br.edu.ufrn.promed.service;

import br.edu.ufrn.promed.mapper.PessoaMapper;
import br.edu.ufrn.promed.model.Pessoa;
import br.edu.ufrn.promed.model.PessoaDto;
import br.edu.ufrn.promed.repository.PessoaRepository;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final PessoaMapper pessoaMapper;

    public PessoaService(PessoaRepository pessoaRepository, PessoaMapper pessoaMapper) {
        this.pessoaRepository = pessoaRepository;
        this.pessoaMapper = pessoaMapper;
    }

    public Pessoa cadastro(PessoaDto pessoaDto) {
        var pessoa = pessoaMapper.toPessoa(pessoaDto);
        pessoaRepository.cadastro(pessoa);
        return pessoa;
    }
}
