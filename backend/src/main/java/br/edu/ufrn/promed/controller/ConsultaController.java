package br.edu.ufrn.promed.controller;

import br.edu.ufrn.promed.dto.request.ConsultaRequestDto;
import br.edu.ufrn.promed.dto.response.ConsultaResponseDto;
import br.edu.ufrn.promed.service.ConsultaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("consulta")
public class ConsultaController {

    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @PostMapping
    public ResponseEntity<ConsultaResponseDto> cadastro(@RequestBody ConsultaRequestDto consultaRequestDto) throws SQLException {
        var consulta = this.consultaService.cadastrarConsulta(consultaRequestDto);
        return ResponseEntity.ok(consulta);
    }
}
