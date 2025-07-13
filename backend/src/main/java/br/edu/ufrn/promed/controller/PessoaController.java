package br.edu.ufrn.promed.controller;

import br.edu.ufrn.promed.dto.request.LoginRequestDto;
import br.edu.ufrn.promed.model.Pessoa;
import br.edu.ufrn.promed.dto.request.PessoaRequestDto;
import br.edu.ufrn.promed.service.AuthService;
import br.edu.ufrn.promed.service.PessoaService;
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
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto){
        if (loginRequestDto.getEmail() == null || loginRequestDto.getPassword() == null){
            return ResponseEntity.badRequest().body("Email e senha são obrigatórios");
        }

        boolean isAuthenticated = authService.authenticate(loginRequestDto);

        if(isAuthenticated){
            return ResponseEntity.ok("Login bem-sucessido!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas.");
        }
    }

    @PutMapping("/deslogar/{cpf}")
    public ResponseEntity<Void> deslogar(@PathVariable String cpf){
        pessoaService.inativar(cpf);
        return ResponseEntity.noContent().build();
    }
}
