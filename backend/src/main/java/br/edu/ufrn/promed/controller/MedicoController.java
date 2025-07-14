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

    @PutMapping("/crm/{medicoCrm}/horarios/{horarioId}")
    public ResponseEntity<Void> removerHorarioAtendimento(@PathVariable int medicoCrm, @PathVariable int horarioId) {
        medicoService.cancelarHorarioAtendimento(medicoCrm, horarioId);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{horarioId}/recorrencia/{recorrenciaId}")
    public ResponseEntity<Void> cancelarHorario(
            @PathVariable int horarioId,
            @PathVariable int recorrenciaId) {

        medicoService.cancelarHorarioRecorrencia(horarioId,recorrenciaId);

        return ResponseEntity.ok().build(); // Retorna 200 OK
    }

    @PutMapping("/{crm}/agenda-recorrente")
    public ResponseEntity<Void> cancelarTodaAgendaRecorrente(@PathVariable int crm) {
        medicoService.cancelarTodaAgendaRecorrente(crm);
        return ResponseEntity.noContent().build();
    }
}
