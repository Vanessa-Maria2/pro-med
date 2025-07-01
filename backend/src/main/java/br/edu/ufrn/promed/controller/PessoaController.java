package br.edu.ufrn.promed.controller;

import br.edu.ufrn.promed.model.Pessoa;
import br.edu.ufrn.promed.model.PessoaDto;
import br.edu.ufrn.promed.service.PessoaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pessoa")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping
    public ResponseEntity<Pessoa> cadastro(@RequestBody PessoaDto pessoaDto) {
        var pessoa = pessoaService.cadastro(pessoaDto);
        return ResponseEntity.ok(pessoa);
    }
}
