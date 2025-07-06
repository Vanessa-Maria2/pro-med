package br.edu.ufrn.promed.service;

import br.edu.ufrn.promed.mapper.PacienteMapper;
import br.edu.ufrn.promed.model.Paciente;
import br.edu.ufrn.promed.dto.request.PacienteRequestDto;
import br.edu.ufrn.promed.dto.response.PacienteResponseDto;
import br.edu.ufrn.promed.repository.PacienteRepository;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    private final PacienteMapper pacienteMapper;

    public PacienteService(PacienteRepository pacienteRepository, PacienteMapper pacienteMapper) {
        this.pacienteRepository = pacienteRepository;
        this.pacienteMapper = pacienteMapper;
    }

    public PacienteResponseDto cadastrarPaciente(PacienteRequestDto pacienteRequestDto) {
        Paciente paciente = pacienteMapper.toPaciente(pacienteRequestDto);
        this.pacienteRepository.cadastrarPaciente(paciente);
        return pacienteMapper.toPacienteResponseDto(paciente);
    }
}
