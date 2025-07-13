package br.edu.ufrn.promed.service;

import br.edu.ufrn.promed.mapper.PessoaMapper;
import br.edu.ufrn.promed.model.Pessoa;
import br.edu.ufrn.promed.dto.request.PessoaRequestDto;
import br.edu.ufrn.promed.repository.PessoaRepository;
import br.edu.ufrn.promed.util.PasswordUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final PessoaMapper pessoaMapper;

    public PessoaService(PessoaRepository pessoaRepository, PessoaMapper pessoaMapper, PasswordUtil passwordUtil) {
        this.pessoaRepository = pessoaRepository;
        this.pessoaMapper = pessoaMapper;
    }

    public Pessoa cadastro(PessoaRequestDto pessoaDto) {
        var pessoa = pessoaMapper.toPessoa(pessoaDto);
        pessoaRepository.cadastro(pessoa);
        return pessoa;
    }

    public Optional<Pessoa> findByEmail(String email){
        return pessoaRepository.findByEmail(email);
    }

    public Optional<Pessoa> findByCpf(String cpf){return pessoaRepository.findByCpf(cpf);}


    public void inativar(String cpf){
        pessoaRepository.inativar(cpf);
    }

    @Transactional
    public Pessoa editar(String cpf, PessoaRequestDto pessoaDto) {
        Pessoa pessoa = pessoaRepository.findByCpf(cpf)
                .orElseThrow(() -> new NoSuchElementException("Pessoa não encontrada"));

        if (pessoaDto.getNome() != null) {
            pessoa.setNome(pessoaDto.getNome());
        }
        if (pessoaDto.getSobrenome() != null) {
            pessoa.setSobrenome(pessoaDto.getSobrenome());
        }
        if (pessoaDto.getEmail() != null) {
            pessoa.setEmail(pessoaDto.getEmail());
        }
        if (pessoaDto.getEndereco() != null) {
            pessoa.setEndereco(pessoaDto.getEndereco());
        }
        if (pessoaDto.getDataNascimento() != null) {
            pessoa.setDataNascimento(pessoaDto.getDataNascimento());
        }

    	pessoaRepository.editar(pessoa);
        return pessoa;
    }
}



