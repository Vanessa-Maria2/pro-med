package br.edu.ufrn.promed.service;

import br.edu.ufrn.promed.mapper.PacienteMapper;
import br.edu.ufrn.promed.model.Paciente;
import br.edu.ufrn.promed.dto.request.PacienteRequestDto;
import br.edu.ufrn.promed.dto.response.PacienteResponseDto;
import br.edu.ufrn.promed.repository.HorarioAtendimentoRepository;
import br.edu.ufrn.promed.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

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

    public void liberarHorario(int horarioAtendimentoId, String pacienteCpf){
        int horarioliberado = horarioAtendimentoRepository.liberarHorario(horarioAtendimentoId, pacienteCpf);

        if(horarioliberado == 0){
            throw new NoSuchElementException("Paciente não encontrado");
        }
    }
}
