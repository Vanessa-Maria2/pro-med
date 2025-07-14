package br.edu.ufrn.promed.service;

import br.edu.ufrn.promed.mapper.PacienteMapper;
import br.edu.ufrn.promed.model.Paciente;
import br.edu.ufrn.promed.dto.request.PacienteRequestDto;
import br.edu.ufrn.promed.dto.response.PacienteResponseDto;
import br.edu.ufrn.promed.repository.HorarioAtendimentoRepository;
import br.edu.ufrn.promed.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    private final PacienteMapper pacienteMapper;
    private final HorarioAtendimentoRepository horarioAtendimentoRepository;

    public PacienteService(PacienteRepository pacienteRepository, PacienteMapper pacienteMapper, HorarioAtendimentoRepository horarioAtendimentoRepository) {
        this.pacienteRepository = pacienteRepository;
        this.pacienteMapper = pacienteMapper;
        this.horarioAtendimentoRepository = horarioAtendimentoRepository;
    }

    public PacienteResponseDto cadastrarPaciente(PacienteRequestDto pacienteRequestDto) {
        Paciente paciente = pacienteMapper.toPaciente(pacienteRequestDto);
        this.pacienteRepository.cadastrarPaciente(paciente);
        return pacienteMapper.toPacienteResponseDto(paciente);
    }

    public void cancelarAgendamento(int horarioAtendimentoId, String pacienteCpf) {
        try {
            int linhasAfetadas = horarioAtendimentoRepository.liberarHorarioDoPaciente(horarioAtendimentoId, pacienteCpf);

            if(linhasAfetadas == 0){
                throw new IllegalStateException("Não foi possível cancelar. O agendamento pode não existir ou não pertence a este paciente.");
            }
        } catch (SQLException e){
            throw new RuntimeException("Erro de banco de dados ao tentar cancelar o agendamento.", e);
        }
    }
}
