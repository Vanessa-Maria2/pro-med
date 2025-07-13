package br.edu.ufrn.promed.controller;

import br.edu.ufrn.promed.dto.response.DoencaResponseDto;
import br.edu.ufrn.promed.service.DoencaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("doenca")
public class DoencaController {

    private final DoencaService doencaService;

    public DoencaController(DoencaService doencaService) {
        this.doencaService = doencaService;
    }

    @GetMapping
    public ResponseEntity<List<DoencaResponseDto>> listar() {
        return ResponseEntity.ok(this.doencaService.getAllDoencas());
    }
}
