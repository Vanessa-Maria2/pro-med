package br.edu.ufrn.promed.service;

import br.edu.ufrn.promed.dto.response.HorarioAtendimentoResponseDto;
import br.edu.ufrn.promed.repository.HorarioAtendimentoRepository;
import org.springframework.stereotype.Service;

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

}
