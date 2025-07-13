package br.edu.ufrn.promed.controller;

import br.edu.ufrn.promed.dto.response.HorarioAtendimentoResponseDto;
import br.edu.ufrn.promed.service.HorarioAtendimentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("horario-atendimento")
public class HorarioAtendimentoController {

    private final HorarioAtendimentoService horarioAtendimentoService;

    public HorarioAtendimentoController(HorarioAtendimentoService horarioAtendimentoService) {
        this.horarioAtendimentoService = horarioAtendimentoService;
    }

    @GetMapping
    public ResponseEntity<List<HorarioAtendimentoResponseDto>> listaHorarioAtendimento() {
        var resultado = this.horarioAtendimentoService.buscarTodos();
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/buscarPorMedico/{cpf}")
    public ResponseEntity<List<HorarioAtendimentoResponseDto>> listaHorarioAtendimentoPorMedico(@PathVariable("cpf") String Medico_cpf) {
        var resultado = this.horarioAtendimentoService.buscarPorMedico(Medico_cpf);
        return ResponseEntity.ok(resultado);
    }
}
