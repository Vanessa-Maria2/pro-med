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

    public List<HorarioAtendimentoResponseDto> buscarTodos() {
        return this.horarioAtendimentoRepository.buscarTodas();
    }

    public List<HorarioAtendimentoResponseDto> buscarPorMedico(String Medico_cpf) {
        List<HorarioAtendimentoResponseDto> horaraioDtoList = horarioAtendimentoRepository.buscarPorMedico(Medico_cpf);
        return horaraioDtoList;
    }

    public boolean agendarHorarioAtendimento(int horarioAtendimentoId, String cpf) throws SQLException {
        return this.horarioAtendimentoRepository.agendarHorarioAtendimento(horarioAtendimentoId, cpf);
    }
}
