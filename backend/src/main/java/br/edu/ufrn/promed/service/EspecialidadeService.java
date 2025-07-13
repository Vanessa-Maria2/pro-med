package br.edu.ufrn.promed.service;

import br.edu.ufrn.promed.model.Especialidade;
import br.edu.ufrn.promed.repository.EspecialidadeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspecialidadeService {

    private final EspecialidadeRepository especialidadeRepository;

    public EspecialidadeService(EspecialidadeRepository especialidadeRepository) {
        this.especialidadeRepository = especialidadeRepository;
    }

    public List<Especialidade> listarTodasEspecialidades() {
        return especialidadeRepository.listarTodasEspecialidades();
    }
}


