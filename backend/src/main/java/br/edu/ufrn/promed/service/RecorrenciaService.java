package br.edu.ufrn.promed.service;

import br.edu.ufrn.promed.dto.request.RecorrenciaRequestDto;
import br.edu.ufrn.promed.model.Recorrencia;
import br.edu.ufrn.promed.repository.RecorrenciaRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecorrenciaService {

    private final RecorrenciaRepository recorrenciaRepository;

    public RecorrenciaService(RecorrenciaRepository recorrenciaRepository) {
        this.recorrenciaRepository = recorrenciaRepository;
    }

    public RecorrenciaRequestDto cadastrar(RecorrenciaRequestDto recorrenciaRequestDto) throws SQLException {
        this.recorrenciaRepository.cadastro(recorrenciaRequestDto);
        return recorrenciaRequestDto;
    }
    
    public List<LocalDate> gerarDatas(Recorrencia recorrencia) {
        List<LocalDate> data = new ArrayList<>();
        data = this.recorrenciaRepository.gerarDatas(recorrencia.getData_inicio().toLocalDate(), recorrencia.getData_fim().toLocalDate(), recorrencia.getDiaSemanas());
        return data;
    }

}
