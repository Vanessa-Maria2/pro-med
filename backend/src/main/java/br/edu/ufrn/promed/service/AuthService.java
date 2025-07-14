package br.edu.ufrn.promed.service;

import br.edu.ufrn.promed.dto.request.LoginRequestDto;
import br.edu.ufrn.promed.dto.response.PessoaResponseDto;
import br.edu.ufrn.promed.mapper.PessoaMapper;
import br.edu.ufrn.promed.model.Pessoa;
import br.edu.ufrn.promed.repository.PessoaRepository;
import br.edu.ufrn.promed.util.PasswordUtil;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final PasswordUtil passwordUtil;
    private final PessoaRepository pessoaRepository;
    private final PessoaMapper pessoaMapper;

    public AuthService(PasswordUtil passwordUtil, PessoaRepository pessoaRepository, PessoaMapper pessoaMapper) {
        this.passwordUtil = passwordUtil;
        this.pessoaRepository = pessoaRepository;
        this.pessoaMapper = pessoaMapper;
    }

    public PessoaResponseDto authenticate(LoginRequestDto loginRequestDto){
        Optional<Pessoa> pessoaOpt = pessoaRepository.findByEmail(loginRequestDto.getEmail());

        if(pessoaOpt.isEmpty()){
            throw new RuntimeException("Pesosa não encontrada");
        }

        Pessoa pessoa = pessoaOpt.get();

        boolean passwordMatch = passwordUtil.matchPassword(loginRequestDto.getPassword(), pessoa.getSenha());

        if (!passwordMatch) {
            throw new RuntimeException("Senha incorreta");
        }

        return pessoaMapper.toPessoaResponseDto(pessoa);
    }
}
