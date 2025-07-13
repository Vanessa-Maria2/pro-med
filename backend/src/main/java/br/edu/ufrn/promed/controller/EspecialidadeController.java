package br.edu.ufrn.promed.controller;

import br.edu.ufrn.promed.model.Especialidade;
import br.edu.ufrn.promed.service.EspecialidadeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/especialidade")
public class EspecialidadeController {

    private final EspecialidadeService especialidadeService;

    public EspecialidadeController(EspecialidadeService especialidadeService) {
        this.especialidadeService = especialidadeService;
    }

    @GetMapping
    public ResponseEntity<List<Especialidade>> buscarTodasEspecialidades(){
        List<Especialidade> especialidades = especialidadeService.listarTodasEspecialidades();
        return ResponseEntity.ok(especialidades);
    }
}
