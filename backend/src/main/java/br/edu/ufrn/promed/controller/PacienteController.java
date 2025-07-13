package br.edu.ufrn.promed.controller;

import br.edu.ufrn.promed.dto.request.PacienteRequestDto;
import br.edu.ufrn.promed.dto.response.PacienteResponseDto;
import br.edu.ufrn.promed.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<PacienteResponseDto> cadastro(@RequestBody @Valid PacienteRequestDto pacienteRequestDto) {
        var paciente = this.pacienteService.cadastrarPaciente(pacienteRequestDto);
        return ResponseEntity.ok(paciente);
    }

    @PutMapping("/cpf/{cpf}/horarios/{horarioId}")
    public ResponseEntity<Void> liberarHorario(@PathVariable String cpf, @PathVariable int horarioId){
        pacienteService.liberarHorario(horarioId, cpf);
        return ResponseEntity.noContent().build();
    }
}
