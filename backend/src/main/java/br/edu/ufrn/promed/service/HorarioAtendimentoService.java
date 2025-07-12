package br.edu.ufrn.promed.service;

import br.edu.ufrn.promed.dto.response.HorarioAtendimentoResponseDto;
import br.edu.ufrn.promed.model.HorarioAtendimento;
import br.edu.ufrn.promed.repository.HorarioAtendimentoRepository;
import org.springframework.stereotype.Service;

import java.sql.Connection;
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

    public boolean isHorarioAtendimentoDisponivel(int horarioAtendimentoId) {
        return this.horarioAtendimentoRepository.isHorarioAtendimentoDisponivel(horarioAtendimentoId);
    }

    public void ocuparHorario(int horarioAtendimentoId, Connection connection) {
        this.horarioAtendimentoRepository.ocuparHorario(horarioAtendimentoId, connection);
    }
}
