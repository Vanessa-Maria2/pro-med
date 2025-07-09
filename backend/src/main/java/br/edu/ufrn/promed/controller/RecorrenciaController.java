package br.edu.ufrn.promed.controller;

import br.edu.ufrn.promed.dto.request.RecorrenciaRequestDto;
import br.edu.ufrn.promed.model.Recorrencia;
import br.edu.ufrn.promed.service.RecorrenciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("recorrencia")
public class RecorrenciaController {

    private final RecorrenciaService recorrenciaService;

    public RecorrenciaController(RecorrenciaService recorrenciaService) {
        this.recorrenciaService = recorrenciaService;
    }

    @PostMapping
    public ResponseEntity<RecorrenciaRequestDto> cadastrarRecorrencia(@RequestBody RecorrenciaRequestDto recorrenciaRequestDto) {
        var result = this.recorrenciaService.cadastrar(recorrenciaRequestDto);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/gerarDatas")
    public ResponseEntity<List<LocalDate>> gerarDatas(@RequestBody Recorrencia recorrencia) {
        var result = this.recorrenciaService.gerarDatas(recorrencia);
        return ResponseEntity.ok(result);
    }
}
