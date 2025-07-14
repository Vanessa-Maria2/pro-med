package br.edu.ufrn.promed.controller;

import br.edu.ufrn.promed.dto.request.MedicoRequestDto;
import br.edu.ufrn.promed.dto.response.MedicoResponseDto;
import br.edu.ufrn.promed.service.MedicoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @PostMapping
    public ResponseEntity<MedicoResponseDto> cadastro(@RequestBody @Valid MedicoRequestDto medicoRequestDto){
        var medico = medicoService.cadastro(medicoRequestDto);
        return ResponseEntity.ok(medico);
    }

    @GetMapping("/buscarTodos")
    public ResponseEntity<List<MedicoResponseDto>> buscarTodos(){
        var medicos = medicoService.buscarTodos();
        return ResponseEntity.ok(medicos);
    }

    @PutMapping("/cancelar-horario/{horarioId}")
    public ResponseEntity<Void> cancelarHorario(
            @PathVariable int horarioId) {

        medicoService.cancelarHorarioPorId(horarioId);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/cancelar-horario-recorrente/{recorrenciaId}")
    public ResponseEntity<Void> cancelarTodaAgendaRecorrente(@PathVariable int recorrenciaId) {
        medicoService.cancelarAgendaRecorrente(recorrenciaId);
        return ResponseEntity.noContent().build();
    }
}
