package br.edu.ufrn.promed.controller;

import br.edu.ufrn.promed.dto.request.AgendarHorarioAtendimentoRequestDto;
import br.edu.ufrn.promed.dto.response.HorarioAtendimentoResponseDto;
import br.edu.ufrn.promed.service.HorarioAtendimentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("horario-atendimento")
public class HorarioAtendimentoController {

    private final HorarioAtendimentoService horarioAtendimentoService;

    public HorarioAtendimentoController(HorarioAtendimentoService horarioAtendimentoService) {
        this.horarioAtendimentoService = horarioAtendimentoService;
    }

    @GetMapping
    public ResponseEntity<List<HorarioAtendimentoResponseDto>> listaHorarioAtendimento(@RequestParam String status) {
        var resultado = this.horarioAtendimentoService.buscarTodos(status);
        return ResponseEntity.ok(resultado);
    }

    @PostMapping("/agendar")
    public ResponseEntity<String> agendarHorarioAtendimento(@RequestBody AgendarHorarioAtendimentoRequestDto dto) throws SQLException {
        var agendado = this.horarioAtendimentoService.agendarHorarioAtendimento(dto.getHorarioAtendimentoId(), dto.getCpf());
        if (agendado) {
            return ResponseEntity.ok("Horário agendado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Não foi possível agendar o horário.");
        }
    }

    @GetMapping("/buscarPorMedico/{cpf}")
    public ResponseEntity<List<HorarioAtendimentoResponseDto>> listaHorarioAtendimentoPorMedico(@PathVariable("cpf") String Medico_cpf) {
        var resultado = this.horarioAtendimentoService.buscarPorMedico(Medico_cpf);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/buscarPorMedicoStatus/{cpf}")
    public ResponseEntity<List<HorarioAtendimentoResponseDto>> listaHorarioAtendimentoPorMedico(@PathVariable("cpf") String Medico_cpf, @RequestParam String status) {
        var resultado = this.horarioAtendimentoService.buscarPorMedicoPorStatus(Medico_cpf, status);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/buscarPorPaciente/{cpf}")
    public ResponseEntity<List<HorarioAtendimentoResponseDto>> listaHorarioAtendimentoPorPaciente(@PathVariable("cpf") String Paciente_cpf) {
        var resultado = this.horarioAtendimentoService.buscarPorPaciente(Paciente_cpf);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/atendimentosDiarios/{cpf}")
    public ResponseEntity<List<HorarioAtendimentoResponseDto>> atendimentosDiarios(@PathVariable("cpf") String Medico_cpf) {
        var resultado = this.horarioAtendimentoService.atendimentosDiarios(Medico_cpf);
        return ResponseEntity.ok(resultado);
    }

}
