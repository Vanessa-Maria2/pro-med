package br.edu.ufrn.promed.service;

import br.edu.ufrn.promed.dto.request.LoginRequestDto;
import br.edu.ufrn.promed.model.Pessoa;
import br.edu.ufrn.promed.repository.PessoaRepository;
import br.edu.ufrn.promed.util.PasswordUtil;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final PasswordUtil passwordUtil;
    private final PessoaRepository pessoaRepository;

    public AuthService(PasswordUtil passwordUtil, PessoaRepository pessoaRepository) {
        this.passwordUtil = passwordUtil;
        this.pessoaRepository = pessoaRepository;
    }

    public boolean authenticate(LoginRequestDto loginRequestDto){
        Optional<Pessoa> pessoaOpt = pessoaRepository.findByEmail(loginRequestDto.getEmail());

        if(pessoaOpt.isEmpty()){
            return false;
        }

        Pessoa pessoa = pessoaOpt.get();

        return passwordUtil.matchPassword(loginRequestDto.getPassword(), pessoa.getSenha());
    }
}
