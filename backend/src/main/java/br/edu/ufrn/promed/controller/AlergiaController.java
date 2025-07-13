package br.edu.ufrn.promed.controller;

import br.edu.ufrn.promed.dto.response.AlergiaResponseDto;
import br.edu.ufrn.promed.service.AlergiaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/alergia")
public class AlergiaController {

    private final AlergiaService alergiaService;

    public AlergiaController(AlergiaService alergiaService) {
        this.alergiaService = alergiaService;
    }


    @GetMapping
    public ResponseEntity<List<AlergiaResponseDto>> getAll() {
        return ResponseEntity.ok(alergiaService.getAll());
    }

}
