package br.edu.ufrn.promed.service;

import br.edu.ufrn.promed.dto.response.HorarioAtendimentoResponseDto;
import br.edu.ufrn.promed.model.HorarioAtendimento;
import br.edu.ufrn.promed.repository.HorarioAtendimentoRepository;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Service
public class HorarioAtendimentoService {

    private final HorarioAtendimentoRepository horarioAtendimentoRepository;

    public HorarioAtendimentoService(HorarioAtendimentoRepository horarioAtendimentoRepository) {
        this.horarioAtendimentoRepository = horarioAtendimentoRepository;
    }

    public List<HorarioAtendimentoResponseDto> buscarTodos(String status) {
        return this.horarioAtendimentoRepository.buscarTodas(status);
    }

    public List<HorarioAtendimentoResponseDto> buscarPorMedico(String Medico_cpf, String status) {
        List<HorarioAtendimentoResponseDto> horaraioDtoList = horarioAtendimentoRepository.buscarPorMedico(Medico_cpf, status);
        return horaraioDtoList;
    }

    public List<HorarioAtendimentoResponseDto> buscarPorPaciente(String Paciente_cpf) {
        List<HorarioAtendimentoResponseDto> horaraioDtoList = horarioAtendimentoRepository.buscarPorPaciente(Paciente_cpf);
        return horaraioDtoList;
    }

    public List<HorarioAtendimentoResponseDto> atendimentosDiarios(String Medico_cpf) {
        List<HorarioAtendimentoResponseDto> horaraioDtoList = horarioAtendimentoRepository.buscarAtendimentosDiarios(Medico_cpf);
        return horaraioDtoList;
    }


    public boolean agendarHorarioAtendimento(int horarioAtendimentoId, String cpf) throws SQLException {
        return this.horarioAtendimentoRepository.agendarHorarioAtendimento(horarioAtendimentoId, cpf);
    }
}
