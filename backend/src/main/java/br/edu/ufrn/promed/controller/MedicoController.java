package br.edu.ufrn.promed.controller;

import br.edu.ufrn.promed.model.MedicoRequestDto;
import br.edu.ufrn.promed.model.MedicoResponseDto;
import br.edu.ufrn.promed.service.MedicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @PostMapping
    public ResponseEntity<MedicoResponseDto> cadastro(@RequestBody MedicoRequestDto medicoRequestDto){
        var medico = medicoService.cadastro(medicoRequestDto);
        return ResponseEntity.ok(medico);
    }
}
