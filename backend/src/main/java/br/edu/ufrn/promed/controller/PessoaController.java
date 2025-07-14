package br.edu.ufrn.promed.controller;

import br.edu.ufrn.promed.dto.request.LoginRequestDto;
import br.edu.ufrn.promed.dto.response.AlterarSenhaRequestDto;

import br.edu.ufrn.promed.dto.response.PessoaResponseDto;

import br.edu.ufrn.promed.model.Pessoa;
import br.edu.ufrn.promed.dto.request.PessoaRequestDto;
import br.edu.ufrn.promed.service.AuthService;
import br.edu.ufrn.promed.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pessoa")
public class PessoaController {

    private final PessoaService pessoaService;
    private final AuthService authService;

    public PessoaController(PessoaService pessoaService, AuthService authService) {
        this.pessoaService = pessoaService;
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<Pessoa> cadastro(@RequestBody PessoaRequestDto pessoaDto) {
        var pessoa = pessoaService.cadastro(pessoaDto);
        return ResponseEntity.ok(pessoa);
    }

    @PostMapping("/login")
    public ResponseEntity<PessoaResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
        if (loginRequestDto.getEmail() == null || loginRequestDto.getPassword() == null){
            throw new RuntimeException("Email e senha são obrigatórios");
        }

        try {
            PessoaResponseDto pessoa = authService.authenticate(loginRequestDto);
            return ResponseEntity.ok(pessoa);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping("/deslogar/{cpf}")
    public ResponseEntity<Void> deslogar(@PathVariable String cpf){
        pessoaService.inativar(cpf);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{email}/senha")
    public ResponseEntity<Void> alterarSenha(@PathVariable String email, @Valid @RequestBody AlterarSenhaRequestDto dto){
        pessoaService.alterarSenha(email, dto.getSenhaAtual(), dto.getNovaSenha(), dto.getConfirmarSenha());
        return ResponseEntity.noContent().build();
    }
}
