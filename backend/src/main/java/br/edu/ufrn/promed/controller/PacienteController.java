package br.edu.ufrn.promed.controller;

import br.edu.ufrn.promed.dto.request.PacienteRequestDto;
import br.edu.ufrn.promed.dto.response.PacienteResponseDto;
import br.edu.ufrn.promed.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
