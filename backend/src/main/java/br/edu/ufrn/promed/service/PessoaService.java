package br.edu.ufrn.promed.service;

import br.edu.ufrn.promed.mapper.PessoaMapper;
import br.edu.ufrn.promed.model.LoginRequestDto;
import br.edu.ufrn.promed.model.Pessoa;
import br.edu.ufrn.promed.model.PessoaDto;
import br.edu.ufrn.promed.repository.PessoaRepository;
import br.edu.ufrn.promed.util.PasswordUtil;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final PessoaMapper pessoaMapper;

    public PessoaService(PessoaRepository pessoaRepository, PessoaMapper pessoaMapper, PasswordUtil passwordUtil) {
        this.pessoaRepository = pessoaRepository;
        this.pessoaMapper = pessoaMapper;
    }

    public Pessoa cadastro(PessoaDto pessoaDto) {
        var pessoa = pessoaMapper.toPessoa(pessoaDto);
        pessoaRepository.cadastro(pessoa);
        return pessoa;
    }

    public Optional<Pessoa> findByEmail(String email){
        return pessoaRepository.findByEmail(email);
    }

}
