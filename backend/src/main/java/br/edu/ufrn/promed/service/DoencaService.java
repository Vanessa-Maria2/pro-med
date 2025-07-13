package br.edu.ufrn.promed.service;

import br.edu.ufrn.promed.dto.response.DoencaResponseDto;
import br.edu.ufrn.promed.repository.DoencaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoencaService {

    private final DoencaRepository doencaRepository;

    public DoencaService(DoencaRepository doencaRepository) {
        this.doencaRepository = doencaRepository;
    }

    public List<DoencaResponseDto> getAllDoencas() {
        return this.doencaRepository.buscarTodos();
    }
}
