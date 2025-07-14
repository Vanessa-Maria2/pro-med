package br.edu.ufrn.promed.service;

import br.edu.ufrn.promed.mapper.PessoaMapper;
import br.edu.ufrn.promed.model.Pessoa;
import br.edu.ufrn.promed.dto.request.PessoaRequestDto;
import br.edu.ufrn.promed.repository.PessoaRepository;
import br.edu.ufrn.promed.util.PasswordUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.parser.Entity;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final PessoaMapper pessoaMapper;
    private final PasswordUtil passwordUtil;

    public PessoaService(PessoaRepository pessoaRepository, PessoaMapper pessoaMapper, PasswordUtil passwordUtil) {
        this.pessoaRepository = pessoaRepository;
        this.pessoaMapper = pessoaMapper;
        this.passwordUtil = passwordUtil;
    }

    public Pessoa cadastro(PessoaRequestDto pessoaDto) {
        var pessoa = pessoaMapper.toPessoa(pessoaDto);
        pessoaRepository.cadastro(pessoa);
        return pessoa;
    }

    public Optional<Pessoa> findByEmail(String email){
        return pessoaRepository.findByEmail(email);
    }

    public void inativar(String cpf){
        pessoaRepository.inativar(cpf);
    }

    @Transactional
    public void alterarSenha(String email, String senhaAtual, String novaSenha, String confirmarNovaSenh){

        if(!novaSenha.equals(confirmarNovaSenh)){
            throw new IllegalArgumentException("A nova senha e a confirmação de senha não correspondem.");
        }

        Optional<Pessoa> pessoaOpt = pessoaRepository.findByEmail(email);

        if(pessoaOpt.isEmpty()){
            throw new NoSuchElementException("Usuário não encontrado com o email: " + email);
        }

        Pessoa pessoa = pessoaOpt.get();

        boolean senhaCorrespondente = passwordUtil.matchPassword(senhaAtual, pessoa.getSenha());
        if(!senhaCorrespondente){
            throw new SecurityException("A senha atual está incorreta.");
        }

        String novaSenhaHash = passwordUtil.hashPassword(novaSenha);
        pessoaRepository.alterarSenha(email, novaSenhaHash);
    }

}
