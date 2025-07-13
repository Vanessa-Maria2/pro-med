package br.edu.ufrn.promed.service;

import br.edu.ufrn.promed.dto.response.AlergiaResponseDto;
import br.edu.ufrn.promed.model.Alergia;
import br.edu.ufrn.promed.repository.AlergiaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlergiaService {

    private final AlergiaRepository alergiaRepository;

    public AlergiaService(AlergiaRepository alergiaRepository) {
        this.alergiaRepository = alergiaRepository;
    }

    public List<AlergiaResponseDto> getAll() {
        return this.alergiaRepository.buscarTodos();
    }
}
